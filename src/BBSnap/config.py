#!/usr/bn/python3

import configparser
import requests
import os
import sys
import shlex
import subprocess

CONFIG = "config.ini"
SERVER = "http://brabo2.ddns.net:555/init"

#keep trying untill config is written
print("starting config")
while(not os.path.isfile(CONFIG)):
    print("no config file found")
    try:
        config = configparser.ConfigParser()
        req = requests.get(SERVER)
        print(req)
        reqjson = req.json()
        print(reqjson)
        config['BASE'] = {
            'ID': reqjson['id'],
            'cert': reqjson['cert'],
            'psk': reqjson['psk'],
            'ssid': reqjson['ssid'],
            'MQTT_BROKER': reqjson['mqttbroker'],
            'BROKER_PORT': reqjson['brokerport'],
            'URL': "http://brabo2.ddns.net:555/photo",
            }
        #connect to ssid?
        config['HARDWARE'] = {
            'LED_PIN': 17,
            'INPUT': 18,
            }

        ssid = shlex.quote(config['BASE']['ssid'])
        print(ssid)
        psk = shlex.quote(config['BASE']['psk'])
        print(psk)
        command = f"nmcli device wifi rescan && sleep 5 && nmcli device wifi connect {ssid} password {psk}"
        print(command)
        subprocess.run(command, shell=True)
        with open(CONFIG, "w") as configfile:
            config.write(configfile)
    except KeyError as ke:
        print(ke);
        raise ke
sys.exit(0)

