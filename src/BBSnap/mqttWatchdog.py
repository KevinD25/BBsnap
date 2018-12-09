#!/usr/bin/python3
import paho.mqtt.client as mqtt
import subprocess
import os
from BBS_Config import *

def on_connect(client, userdata, flags, rc):
    print("connected with code " + str(rc))
    print("subbing to "+str(UNIT_ID))
    client.subscribe(str(UNIT_ID))

def on_message(client, userdata, msg):
    print("message:")
    print(str(msg.topic)+" "+str(msg.payload))
    if msg.payload == b'photo':
        print("taking pic")
        #subprocess.Popen('./takePicture.py', shell=True)
    elif msg.payload == b'toggle':
        toggle_disable()

def toggle_disable():
    if(os.path.isfile('./DISABLE')):
        print("is a file")
        os.remove('./DISABLE')
    else:
        print("is not file")
        open('./DISABLE', 'w').close()

if __name__ == '__main__':
    client = mqtt.Client(str(UNIT_ID))
    client.on_connect = on_connect
    client.on_message = on_message
    client.connect(MQTT_BROKER, BROKER_PORT, 60)

    client.loop_forever()
