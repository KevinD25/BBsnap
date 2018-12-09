#!/usr/bin/python3

import camera
import connection

UNIT_ID = 10    # device ID to be used in database calls

def disabled():
    return false

if( __name__ == "__main__"):
    cam = camera.Cam()
    conn = connection.Connection()

    print("taking picture")
    if ( disabled() ):
        print("disabled")
    else:
        # actually take picture
        filename = cam.take_pic()
        conn.upload_file(filename, UNIT_ID)
