#!/usr/bin/python3
from flask import Flask, request, jsonify
from flask_sqlalchemy import SQLAlchemy
from werkzeug.utils import secure_filename

PHOTO_ROOT = "./images/"

app = Flask(__name__)

app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql://iot:jorenjamar@localhost/IOT'
db = SQLAlchemy(app)

class Photo(db.Model):
	__tablename__ = 'Foto'
	id = db.Column('id', db.Integer, primary_key = True)
	lesid = db.Column('lesid', db.Integer)
	naam = db.Column('naam', db.Unicode)

@app.route('/test')
def test():
	examples = Photo.query.all()

	output = []

	for ex in examples:
		ex_data = {}
		ex_data['id'] = ex.id
		ex_data['lesid'] = ex.lesid
		ex_data['naam'] = ex.naam
		output.append(ex_data)

	return jsonify({'examples' : output})

@app.route('/photoInfo', methods=['GET'])
def get_all_photosInfo():
	examples = Photo.query.all()
	output = []

	for ex in examples:
		ex_data = {}
		ex_data['id'] = ex.id
		ex_data['lesid'] = ex.lesid
		ex_data['naam'] = ex.naam
		output.append(ex_data)

	return jsonify({'examples' : output})

@app.route('/photoInfo/<photo_id>', methods=['GET'])
def get_photoInfo():
	examples = Photo.query.all()

	output = []

	for ex in examples:
		ex_data = {}
		ex_data['id'] = ex.id
		ex_data['lesid'] = ex.lesid
		ex_data['naam'] = ex.naam
		output.append(ex_data)

	return jsonify({'examples' : output})

@app.route('/photo', methods=['POST'])
def create_photo():
	print("create photo")
	# get picture
	print(request.files)
	if ('' not in request.files):
		# return error
		print("file not in files")
	file = request.files['']
	filename = secure_filename(file.filename)

	# save picture
	file.save(PHOTO_ROOT + filename)

	# return confirmation
	return jsonify({'message': 'New photo added!'})

@app.route('/photo/<photo_id>', methods=['DELETE'])
def delete_photo():
	return ''


if __name__ == '__main__':
	app.run(host='0.0.0.0')

# tutorial die kan bekeken worden: https://www.youtube.com/watch?v=WxGBoY5iNXY
# sqlalchemy tutorial: https://www.youtube.com/watch?v=Tu4vRU4lt6k
