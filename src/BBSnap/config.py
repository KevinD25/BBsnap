#!/usr/bn/python3

import configparser
import requests
import os

CONFIG = "config.ini"
SERVER = "http://brabo2.ddns.net:555/init"

if(os.path.isfile(CONFIG)):
    #config exists, this should be fine
    sys.exit
else:
    config = configparser.ConfigParser()
    req = requests.get(SERVER)
    print(req)
    print(req.json())
    config['id'] = req.json()['id']
    # connect to ssid?
    config['LED_PIN'] = 17  # pin of the enable/disable led, also stores enable/disable state
    config['INPUT'] = 18 # pin of IR receiver
    config['URL'] = "http://brabo2.ddns.net:555/photo" #URL of photo post endpoint
    config['MQTT_BROKER'] = "brabo2.ddns.net"   # addres of mqtt broker
    config['BROKER_PORT'] = 1883          # port of mqtt at broker
    with open(CONFIG, "w") as configfile:
        config.write(configfile)
    sys.exit



