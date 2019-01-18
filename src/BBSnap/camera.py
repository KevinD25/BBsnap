from picamera import PiCamera
import time

class Cam :
    def __init__(self):
        self.camera = PiCamera()
        self.camera.resolution = (1920, 1080)
        self.camera.framerate = 5

    # takes a picture and writes it away to a file named yymmdd-HHMMSS.jpg
    # returns: file name
    def take_pic(self):
        self.camera.start_preview()
        time.sleep(2)
        name = time.strftime("%y%m%d-%H%M%S.jpg");
        self.camera.capture('./img/' + name)
        self.camera.stop_preview()
        return name

if( __name__ == "__main__"):
    cam = Cam()
    print("file written to " + cam.take_pic())

