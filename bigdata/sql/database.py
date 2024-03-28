from sqlalchemy import *
from sqlalchemy.orm import sessionmaker
from config.config import read_config

DB_URL = read_config("DB", "URL")


class EngineConn:
    def __init__(self):
        self.engine = create_engine(DB_URL, pool_recycle=500)

    def session_maker(self):
        session = sessionmaker(bind=self.engine)
        session = session()
        return session

    def connection(self):
        conn = self.engine.connect()
        return conn

#
# def mysql_create_session():
#     conn = pymysql.connect(
#         host="j10a603.p.ssafy.io",
#         user="root",
#         password="qtwe153",
#         db='coursefree',
#         charset="utf8")
#     cur = conn.cursor()
#     return conn, cur
