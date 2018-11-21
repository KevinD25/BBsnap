#!/usr/bin/python3

import pigpio
import math
import os
import time
import camera
import connection

UNIT_ID = 10

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



if __name__ == "__main__":
    #TODO uncomment
    cam = camera.Cam()
    pi = pigpio.pi()
    INPUT = 18
    pi.set_mode(INPUT, pigpio.INPUT);
    conn = connection.Connection()

    while (False):
        # wait for activity
        if pi.wait_for_edge(INPUT, pigpio.RISING_EDGE, 10):
            length = measure_W_length(INPUT)

            #if average wavelength is close enough to 3700ms
            if ((length > 3100) and (length < 4100)):
                #take photo
                filename = cam.take_pic()
                conn.upload_file(filename, UNIT_ID)
            #close enough to 1200ms
            elif ((length > 1000) and (length < 1500)):
            #toggle disable
                print("disable/enable")
            #else signal is unimportant, do nothing
            time.sleep(1)
        else:
            print("waiting")

