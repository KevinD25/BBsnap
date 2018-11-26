import requests

class Connection:
    URL = "http://brabo2.ddns.net:555/photo"
    #URL = "http://localhost:5000/photo"

    def upload_file(self, filename, id):
        files = {'file': (filename, open('./img/' + filename, 'rb'))}
        req = requests.post(self.URL, files=files, data={'id': id})

if (__name__ == "__main__"):
    c = Connection()
    c.upload_file("./img/181112-140400.jpg", 1)
