#!/usr/bin/python3
from flask import Flask, request, jsonify
import subprocess
import os

app = Flask(__name__)

@app.route('/takephoto/')
def take_photo():
    subprocess.Popen('./takePicture.py', shell=True)

@app.route('/toggleDisable/')
def toggle_disable():
    if(os.path.isfile('DISABLE')):
        od.remove('DISABLE')
    else:
        os.open('DISABLE')


if __name__ == '__main__':
	app.run(host='0.0.0.0')

# tutorial die kan bekeken worden: https://www.youtube.com/watch?v=WxGBoY5iNXY
# sqlalchemy tutorial: https://www.youtube.com/watch?v=Tu4vRU4lt6k
