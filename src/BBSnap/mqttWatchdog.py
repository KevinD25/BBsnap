#!/usr/bin/python3
import paho.mqtt.client as mqtt
import subprocess
import os
import configparser

config = configparser.ConfigParser()
config.read('config.ini')
UNIT_ID = int(config['BASE']['ID'])
MQTT_BROKER = config['BASE']['MQTT_BROKER']
BROKER_PORT = int(config['BASE']['BROKER_PORT'])

def on_connect(client, userdata, flags, rc):
    print("connected with code " + str(rc))
    print("subbing to "+str(UNIT_ID))
    client.subscribe(str(UNIT_ID))

def on_message(client, userdata, msg):
    print("message:")
    print(str(msg.topic)+" "+str(msg.payload))
    if msg.payload == b'photo':
        print("taking pic")
        subprocess.Popen('python3 ./takePicture.py', shell=True)
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
    print("connecting to " + MQTT_BROKER + " on port " + str(BROKER_PORT))
    client.connect(MQTT_BROKER, BROKER_PORT, 60)

    client.loop_forever()
