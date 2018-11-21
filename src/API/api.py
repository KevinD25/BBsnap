#!/usr/bin/python3
from flask import Flask, request, jsonify
from flask_sqlalchemy import SQLAlchemy
from werkzeug.utils import secure_filename
import os

PHOTO_ROOT = "./images/"

app = Flask(__name__)

app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql://iot:jorenjamar@localhost/IOT'
db = SQLAlchemy(app)

class Foto(db.Model):
	__tablename__ = 'Foto'
	id = db.Column('id', db.Integer, primary_key = True)
	naam = db.Column('naam', db.Unicode)
	lesid = db.Column('lesid', db.Integer, db.ForeignKey('Les.id'))

class Les(db.Model):
	__tablename__ = 'Les'
	id = db.Column('id', db.Integer, primary_key = True)
	lokaalid = db.Column('lokaalid', db.Integer)
	vakid = db.Column('vakid', db.Integer)
	klasid = db.Column('vakid', db.Integer)
	starttijd = db.Column('starttijd', db.DateTime)
	eindtijd = db.Column('eindtijd', db.DateTime)
	fotos = db.relationship('Foto', backref='foto', lazy=True)

class Klas(db.Model):
	__tablename__ = 'Klas'
	id = db.Column('id', db.Integer, primary_key = True)
	richtingid = db.Column('richtingid', db.Integer)
	naam = db.Column('naam', db.Integer)

class Lokaal(db.Model):
	__tablename__ = 'Lokaal'
	id = db.Column('id', db.Integer, primary_key = True)
	naam = db.Column('naam', db.Unicode)
	gebouw = db.Column('gebouw', db.Unicode)

class Prof(db.Model):
	__tablename__ = 'Prof'
	id = db.Column('id', db.Integer, primary_key = True)
	naam = db.Column('naam', db.Unicode)

class Richting(db.Model):
	__tablename__ = 'Richting'
	id = db.Column('id', db.Integer, primary_key = True)
	naam = db.Column('naam', db.Unicode)

class Vak(db.Model):
	__tablename__ = 'Vak'
	id = db.Column('id', db.Integer, primary_key = True)
	profid = db.Column('profid', db.Integer)
	naam = db.Column('naam', db.Unicode)

@app.route('/test')
def test():
	fotos = Foto.query.all()
	lessen = Les.query.all()
	klassen = Klas.query.all()
	lokalen = Lokaal.query.all()
	proffen = Prof.query.all()
	richtingen = Richting.query.all()
	vakken = Vak.query.all()

	output = []

	for foto in fotos:
		foto_data = {}
		foto_data['id'] = foto.id
		foto_data['naam'] = foto.naam
		foto_data['lesStartTijd'] = lessen[0].starttijd
		foto_data['lesEindTijd'] = lessen[0].eindtijd
		foto_data['lokaal'] = lokalen[0].naam
		foto_data['lokaalGebouw'] = lokalen[0].gebouw
		foto_data['vak'] = vakken[0].naam
		foto_data['klas'] = klassen[0].naam
		foto_data['prof'] = proffen[0].naam
		foto_data['richting'] = richtingen[0].naam

		output.append(foto_data)

	return jsonify({'fotos' : output})

@app.route('/photoInfo', methods=['GET'])
def get_all_photosInfo():
	fotos = Foto.query.all()
	lessen = Les.query.all()
	klassen = Klas.query.all()
	lokalen = Lokaal.query.all()
	proffen = Prof.query.all()
	richtingen = Richting.query.all()
	vakken = Vak.query.all()

	output = []

	for foto in fotos:
		foto_data = {}
		foto_data['id'] = foto.id
		foto_data['naam'] = foto.naam
		foto_data['lesStartTijd'] = lessen[0].starttijd
		foto_data['lesEindTijd'] = lessen[0].eindtijd
		foto_data['lokaal'] = lokalen[0].naam
		foto_data['lokaalGebouw'] = lokalen[0].gebouw
		foto_data['vak'] = vakken[0].naam
		foto_data['klas'] = klassen[0].naam
		foto_data['prof'] = proffen[0].naam
		foto_data['richting'] = richtingen[0].naam

		output.append(foto_data)

	return jsonify({'fotos' : output})

@app.route('/photoInfo/<photo_id>', methods=['GET'])
def get_photoInfo():
	examples = Foto.query.all()

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
	if not ('file' in request.files):
		# return error
		print("file not in files")
		response = jsonify({'error': 'no file found'})
	file = request.files['file']
	filename = secure_filename(file.filename)
	id = request.values['id']
	id = secure_filename(id)
	os.mkdir(PHOTO_ROOT + id)
	# save picture
	file.save(PHOTO_ROOT + id + '/' + filename)

	# return confirmation
	return jsonify({'message': 'New photo added!'})

@app.route('/photo/<photo_id>', methods=['DELETE'])
def delete_photo():
	return ''


if __name__ == '__main__':
	app.run(host='0.0.0.0')

# tutorial die kan bekeken worden: https://www.youtube.com/watch?v=WxGBoY5iNXY
# sqlalchemy tutorial: https://www.youtube.com/watch?v=Tu4vRU4lt6k
