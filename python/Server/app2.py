import numpy as np
import time
import tensorflow as tf
from flask_cors import CORS
from tensorflow.keras.models import load_model # 인공지능 관련 라이브러리

from flask import Flask, request
from flask_restx import Resource, Api # flask 서버 관련 라이브러리

import threading

model = load_model('model.h5') # 포즈 추정모델을 사용하기 위함
app = Flask(__name__)
api = Api(app)

#CORS(app)

todos = {}
count = 1
result = {}

#def clearAll():
    #global count = 0
    #global todos = {}


def getSquatResult(name,data):    # 인공지능 적용 함수
    try:
        reshapeData = np.array(list(map(int,splitData)))
        modelPredict = model.predict(np.reshape(reshapeData, (1,2,14)))
        if list(modelPredict[0]).index(max(modelPredict[0])) == 0:
            result[name] = "Stand"
        elif list(modelPredict[0]).index(max(modelPredict[0])) == 1:
            result[name] = "Squat"
        elif list(modelPredict[0]).index(max(modelPredict[0]))==2:
            result[name] = "Bent"
    except:
        return "포즈 추정에 실패하였습니다."

@api.route('/todos')
class TodoPost(Resource):
    def post(self):
        global count
        global todos
        global result
        idx = count
        count += 1
        
        # Rest API 가져오기
        name = request.json.get('name')
        #name = request.form['name']
        postData = request.json.get('data')
        #postData = request.form['data']
        print(name)
        #print(postData)
        
        reshapeData = np.array(list(map(int,postData)))
        #print("splitData",reshapeData)
        modelPredict = model.predict(np.reshape(reshapeData, (1,2,14)))
        if list(modelPredict[0]).index(max(modelPredict[0])) == 0:
            result[name] = "Stand"
        elif list(modelPredict[0]).index(max(modelPredict[0])) == 1:
            result[name] = "Squat"
        elif list(modelPredict[0]).index(max(modelPredict[0]))==2:
            result[name] = "Bent"


        print(result)
        
        return {
            'name': name,
            'data': postData
        }

        
@api.route('/todos/<name>')
class TodoSimple(Resource):
    def get(self, name):
        return {
            'name': name,
            'data': result[name]
        }


    def put(self, todo_id):
        todos[todo_id] = request.json.get('data')
        return {
            'todo_id': todo_id,
            'data': todos[todo_id]
        }
    
    def delete(self, todo_id):
        del todos[todo_id]
        return {
            "delete" : "success"
        }

if __name__ == "__main__":
    app.run(debug=True, host='10.0.2.15', port=5001)
