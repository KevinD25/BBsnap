#!/usr/bin/python3

import camera
import connection
import configparser

config = configparser.ConfigParser()
config.read('config.ini')
UNIT_ID = int(config['BASE']['ID'])

if( __name__ == "__main__"):
    cam = camera.Cam()
    conn = connection.Connection()

    print("taking picture")
    else:
        # actually take picture
        filename = cam.take_pic()
        conn.upload_file(filename, UNIT_ID)
