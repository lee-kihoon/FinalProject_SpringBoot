import numpy as np
import time
import tensorflow as tf
import ssl
import time
from threading import Thread
from flask_cors import CORS
from tensorflow.keras.models import load_model # 인공지능 관련 라이브러리

from flask import Flask, request
from flask_restx import Resource, Api # flask 서버 관련 라이브러리

import threading

squatModel = load_model('model.h5') # 스쿼트 포즈 추정모델을 사용하기 위함
pushupModel = load_model('pushupModel.h5') # 푸쉬업 모델

app = Flask(__name__)
api = Api(app)

CORS(app)

todos = {}
count = 1
result = {}

def AI(exercise, name, postData):
    if exercise == "kind_squat":
            reshapeData = np.array(list(map(int,postData)))
            modelPredict = squatModel.predict(np.reshape(reshapeData, (1,2,14)))
            if list(modelPredict[0]).index(max(modelPredict[0])) == 0:
                result[name] = "Stand"
            elif list(modelPredict[0]).index(max(modelPredict[0])) == 1:
                result[name] = "Squat"
            elif list(modelPredict[0]).index(max(modelPredict[0]))==2:
                result[name] = "Bent"
            #print(result)

    if exercise == "kind_pushup":
            reshapeData = np.array(list(map(int,postData)))
            modelPredict = pushupModel.predict(np.reshape(reshapeData, (1,2,14)))
            if list(modelPredict[0]).index(max(modelPredict[0])) == 0:
                result[name] = "Ready"
            elif list(modelPredict[0]).index(max(modelPredict[0])) == 1:
                result[name] = "Good"
            elif list(modelPredict[0]).index(max(modelPredict[0]))==2:
                result[name] = "Worng"


@api.route('/todos')
class TodoPost(Resource):
    def post(self):
        global count
        global todos
        global result
        idx = count
        count += 1

        exercise = request.json.get('kind')
        name = request.json.get('name')
        postData = request.json.get('data')

        thread = Thread(target=AI, args=(exercise,name,postData))
        thread.daemon = True
        thread.start()
        print("ExerciseKind:", exercise)

        #return {
        #    'name': name,
        #    'data': postData
        #}

        
@api.route('/todos/result')
class TodoSimple(Resource):
    def get(self):
        return {
            'AllData': result,
        }

    #def put(self, todo_id):
    #   todos[todo_id] = request.json.get('data')
    #    return {
    #        'todo_id': todo_id,
    #        'data': todos[todo_id]
    #    }
    

if __name__ == "__main__":
 #   ssl_context = ssl.SSLContext(ssl.PROTOCOL_TLS)
#    ssl_context.load_cert_chain(certfile='cert.pem', keyfile='key.pem')
    app.run(debug=True, host='10.0.2.15' ,port = 5000, threaded = True) #, ssl_context=ssl_context)
