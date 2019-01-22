import requests
from BBS_Config import *

class Connection:
    #URL = "http://localhost:5000/photo"

    def upload_file(self, filename, id):
        files = {'file': (filename, open('./img/' + filename, 'rb'))}
        req = requests.post(URL, files=files, data={'id': id})
        print("sent POST")

if (__name__ == "__main__"):
    c = Connection()
    c.upload_file("./img/181112-140400.jpg", 1)
