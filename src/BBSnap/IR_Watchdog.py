#!/usr/bin/python3

import pigpio
import math
import time
import subprocess

LED_PIN = 17    # pin of the enable/disable led, also stores enable/disable state
INPUT = 18      # pin of IR receiver

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
    avg = avg / len(deltaBuffer)
    return avg

def take_picture():
    print("taking picture")
    subprocess.Popen('./takePicture.py', shell=True)

def toggle_disable():
    print("toggled disable test")

if __name__ == "__main__":
    pi = pigpio.pi()
    pi.set_mode(INPUT, pigpio.INPUT)

    while (True):
        # wait for activity
        if pi.wait_for_edge(INPUT, pigpio.RISING_EDGE, 10):
            length = measure_W_length(INPUT)

            #if average wavelength is close enough to 3700ms
            if ((length > 3100) and (length < 4100)):
                take_picture();
            #close enough to 1200ms
            elif ((length > 1000) and (length < 1500)):
                toggle_disable
            #else signal is unimportant, do nothing
            time.sleep(1)
        else:
            print("waiting")
    #end of while

