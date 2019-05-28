import json
from flask import Flask, Response,request,jsonify
app = Flask(__name__)
class Student(object):
    def __init__(self, name, score):
        self.name = name
        self.score = score
@app.route("/health")
def health():
    result = {'status': 'UP'}
    return Response(json.dumps(result), mimetype='application/json')
@app.route("/test")
def getUser():
    result = {'username': 'python', 'password': 'python'}
    return Response(json.dumps(result), mimetype='application/json')
@app.route("/getStudent")
def getStudent():
    id = request.args.get("id")
    bart = Student('Bart Simpson', 59)
    return Response(json.dumps(bart,default=lambda o: o.__dict__,sort_keys=True, indent=4,ensure_ascii=False), mimetype='application/json')
@app.route("/getStudents")
def getStudents():
    list = []
    bart = Student('Bart Simpson', 59)
    zero = Student('zero Simpson', 59)
    list.append(bart)
    list.append(zero)
    return Response(json.dumps(list,default=lambda o: o.__dict__,sort_keys=True, indent=4,ensure_ascii=False), mimetype='application/json')
@app.route('/upload', methods=['GET', 'POST'])
def upload_file():
    if request.method == 'POST':
        filename = request.files['the_file'].filename
    return Response(filename, mimetype='application/json')
app.run(port=5680, host='localhost')


