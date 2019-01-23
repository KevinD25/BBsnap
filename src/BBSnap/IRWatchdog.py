#!/usr/bin/python3

import pigpio
import math
import time
import subprocess
import configparser

config = configparser.ConfigParser()
config.read('config.ini')
INPUT = int(config['HARDWARE']['INPUT'])

def toggle_disable():
    print("toggle_Disable")
    subprocess.Popen('python3 ./disable.py', shell=True)

# measure wavelength of square signal on pin gpio
# input: gpio   number of input pin
# returns: average wavelength in ms
def measure_W_length(gpio):
    tickBuffer = []
    def count_edge(gpio, level, tick):
        tickBuffer.append(tick)

    # start monitoring
    cb = pi.callback(INPUT, pigpio.RISING_EDGE, count_edge)
    # wait
    time.sleep(0.2)
    # stop monitoring
    cb.cancel()
    # determine waveength
    deltaBuffer = []
    for i in range(0, len(tickBuffer) - 1):
        deltaBuffer.append(tickBuffer[i+1] - tickBuffer[i])
    avg = sum(deltaBuffer)
    if (len(deltaBuffer) == 0):
        return 0
    avg = avg / len(deltaBuffer)
    return avg

def take_picture():
    print("taking picture")
    subprocess.Popen('python3 ./takePicture.py', shell=True)

if __name__ == "__main__":
    pi = pigpio.pi()
    pi.set_mode(INPUT, pigpio.INPUT)

    while (True):
        # wait for activity
        if pi.wait_for_edge(INPUT, pigpio.RISING_EDGE, 10):
            print("rising edge")
            length = measure_W_length(INPUT)
            print(length) 
            #if average wavelength is close enough to 3800ms
            if ((length > 3500) and (length < 4000)):
                print("detected take_picture on IR")
                take_picture()
                time.sleep(2) # sleep as "debounce"
            #close enough to 660ms
            elif ((length > 4000) and (length < 5000)):
                print("detected disable on IR")
                toggle_disable()
                time.sleep(2) # sleep as "debounce"
            #else signal is unimportant, do nothing
            time.sleep(1)
        else:
            print("waiting")
    #end of while

