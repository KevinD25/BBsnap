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
	les = db.relationship('Les', back_populates='fotos')

	def toDict(self, skip = ''):
		ret = {}
		ret['id'] = self.id
		ret['naam'] = self.naam
		if not (skip == 'les'):
			ret['les'] = self.les.toDict('fotos')
		else:
			ret['lesid'] = self.lesid
		return ret

class Les(db.Model):
	__tablename__ = 'Les'
	id = db.Column('id', db.Integer, primary_key = True)
	lokaalid = db.Column('lokaalid', db.Integer, db.ForeignKey('Lokaal.id'))
	vakid = db.Column('vakid', db.Integer, db.ForeignKey('Vak.id'))
	klasid = db.Column('klasid', db.Integer, db.ForeignKey('Klas.id'))
	starttijd = db.Column('starttijd', db.DateTime)
	eindtijd = db.Column('eindtijd', db.DateTime)
	fotos = db.relationship('Foto', back_populates='les', lazy='joined')
	vak = db.relationship('Vak', back_populates='lessen')
	klas = db.relationship('Klas', back_populates='lessen')
	lokaal = db.relationship('Lokaal', back_populates='lessen')

	def toDict(self, skip = ''):
		ret = {}
		ret['id'] = self.id
		if not (skip == 'lokaal'):
			ret['lokaal'] = self.lokaal.toDict()
		else:
			ret['lokaalid'] = self.lokaalid
		if not (skip == 'vak'):
			ret['vak'] = self.vak.toDict()
		else:
			ret['vakid'] = self.vakid
		if not (skip == 'klas'):
			ret['klas'] = self.klas.toDict()
		else:
			ret['klasid'] = self.klasid
		ret['starttijd'] = self.starttijd
		ret['eindtijd'] = self.eindtijd
		return ret

class Klas(db.Model):
	__tablename__ = 'Klas'
	id = db.Column('id', db.Integer, primary_key = True)
	richtingid = db.Column('richtingid', db.Integer, db.ForeignKey('Richting.id'))
	naam = db.Column('naam', db.Integer)
	lessen = db.relationship('Les', back_populates='klas', lazy="joined")
	richting = db.relationship('Richting', back_populates='klassen')

	def toDict(self, skip = ''):
		ret = {}
		ret['id'] = self.id
		ret['naam'] = self.naam
		if not (skip == "richting"):
			ret['richting'] = self.richting.toDict()
		else:
			ret['richtingid'] = self.richtingid
		return ret

class Lokaal(db.Model):
	__tablename__ = 'Lokaal'
	id = db.Column('id', db.Integer, primary_key = True)
	naam = db.Column('naam', db.Unicode)
	gebouw = db.Column('gebouw', db.Unicode)
	lessen = db.relationship('Les', back_populates='lokaal', lazy='joined')

	def toDict(self, skip = ''):
		ret = {}
		ret['id'] = self.id
		ret['naam'] = self.naam
		ret['gebouw'] = self.gebouw
		return ret

class Prof(db.Model):
	__tablename__ = 'Prof'
	id = db.Column('id', db.Integer, primary_key = True)
	naam = db.Column('naam', db.Unicode)
	vakken = db.relationship('Vak', back_populates='prof', lazy = 'joined')

	def toDict(self, skip = ''):
		ret = {}
		ret['id'] = self.id
		ret['naam'] = self.naam
		return ret

class Richting(db.Model):
	__tablename__ = 'Richting'
	id = db.Column('id', db.Integer, primary_key = True)
	naam = db.Column('naam', db.Unicode)
	klassen = db.relationship('Klas', back_populates='richting', lazy = 'joined')

	def toDict(self, skip = ''):
		ret = {}
		ret['id'] = self.id
		ret['naam'] = self.naam
		return ret

class Vak(db.Model):
	__tablename__ = 'Vak'
	id = db.Column('id', db.Integer, primary_key = True)
	profid = db.Column('profid', db.Integer, db.ForeignKey('Prof.id'))
	naam = db.Column('naam', db.Unicode)
	lessen = db.relationship('Les', back_populates='vak', lazy='joined')
	prof = db.relationship('Prof', back_populates = 'vakken')

	def toDict(self, skip = ''):
		ret = {}
		ret['id'] = self.id
		ret['naam'] = self.naam
		if not (skip == 'prof'):
			ret['prof'] = self.prof.toDict()
		else:
			ret['profid'] = self.profid
		return ret

@app.route('/test')
def test():
	fotos = Foto.query.all()
	lessen = Les.query.all()
	klassen = Klas.query.all()
	lokalen = Lokaal.query.all()
	proffen = Prof.query.all()
	richtingen = Richting.query.all()
	vakken = Vak.query.all()

	test = db.session.query(Foto, Les).filter(Foto.lesid == Les.id).all()

	output = []

	for foto in fotos:
		output.append(foto.toDict())

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

	# get picture from request
	print(request.files)
	if not ('file' in request.files):	# incorrect naming
		# return error
		print("file not in files")
		resp = jsonify({'error': 'no file found'})
                resp.status_code = 400
                return resp
	file = request.files['file']

	filename = secure_filename(file.filename)
	id = request.values['id']
	id = secure_filename(id)		# id should be a number, but just to be sure
	folder = PHOTO_ROOT + id
	if not os.path.isdir(folder):		# if folder doesn't exist, make new
		os.mkdir(PHOTO_ROOT + id)

	# save picture
	file.save(PHOTO_ROOT + id + '/' + filename)

	#insert info into DB
	foto = Foto()
	foto.naam = filename
	foto.cameraid = id
	db.session.add(foto)
	db.session.commit()
	# return confirmation
	return jsonify({'message': 'New photo added!'})

@app.route('/photo/<photo_id>', methods=['DELETE'])
def delete_photo():
	return ''


if __name__ == '__main__':
	app.run(host='0.0.0.0')

# tutorial die kan bekeken worden: https://www.youtube.com/watch?v=WxGBoY5iNXY
# sqlalchemy tutorial: https://www.youtube.com/watch?v=Tu4vRU4lt6k
