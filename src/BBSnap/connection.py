import requests

class Connection:
    URL = "http://brabo2.ddns.net:555"

    def upload_file(self, filename):
        #files = {'file': (filename, open(filename, 'rb'))}
        files = {}
        req = requests.post(self.URL, files=files)
        
        reply = requests.post(self.URL, files=files)
        print(reply.text)
