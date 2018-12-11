from picamera import PiCamera
from time import sleep

class Cam :
    camera  #refference to picamera camera

    def __init__(self):
        camera = PiCamera()
        camera.resolution = (1920, 1080)
        camera.framerate = 5

    def takepic():
        camera.start_preview()
        sleep(3)
        camera.capture("./test.jpg")
        camera.stop_preview()


