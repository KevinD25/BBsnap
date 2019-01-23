import requests
import configparser

config = configparser.ConfigParser()
config.read('config.ini')
URL = int(config['BASE']['URL'])
ID = config['BASE']['ID']

req = requests.post(URL + "/disablephoto/" + ID)
print("sent POST")
print(req)

