import pymysql

conn = pymysql.connect(
    user = 'zaba',
    passwd = '0000',
    host = '211.253.100.186',
    port = 8081,
    db = 'health',
    charset = 'utf8'
)

curs = conn.cursor()
sql = "UPDATE CountExercise set Squat = Squat + 1 where UserName = %s"
curs.execute(sql,"ggg1234")
conn.commit()
conn.close()
