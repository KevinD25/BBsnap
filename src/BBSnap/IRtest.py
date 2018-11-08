#!/usr/bin/python3

import pigpio
import math
import os
from datetime import datetime
import time

tickBuffer = []

def count_edge(gpio, level, tick):
    tickBuffer.append(tick)

if __name__ == "__main__":
    
    pi = pigpio.pi()
    INPUT = 18
    pi.set_mode(INPUT, pigpio.INPUT);
    
    while (True):
        # wait for activity
        if pi.wait_for_edge(INPUT, pigpio.RISING_EDGE, 10):
            print("detected change")
            # start monitoring
            tickBuffer = []
            cb = pi.callback(INPUT, pigpio.RISING_EDGE, count_edge)
            # wait
            time.sleep(0.2)
            # stop monitoring
            cb.cancel()
            # determine frequency
            print(tickBuffer)
            deltaBuffer = []
            for i in range(0, len(tickBuffer) - 1):
                deltaBuffer.append(tickBuffer[i+1] - tickBuffer[i])
            
            print("deltaBuff len: {length} {buff}".format(length=len(deltaBuffer), buff=str(deltaBuffer) ))
            avg = sum(deltaBuffer)
            print(avg)
            avg = avg / len(deltaBuffer)
            print(avg)
            # act
            
            #if average wavelength is close enough to 3700ms
            if ((avg > 3100) and (avg < 4100)):
                #take photo
                print("take photo")

            #close enough to 1200ms
            elif ((avg > 1000) and (avg < 1500)):
                #toggle disable
                print("disable/enable")
            
            #else signal is unimportant, do nothing
            time.sleep(1)
        else:
            print("waiting")

