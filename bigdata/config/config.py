from configparser import ConfigParser


def read_config(section, key):
    config = ConfigParser()             # 객체 생성
    config.read("config.ini")           # ini 파일 읽기
    return config.get(section, key)     # section, key 를 통해 value 리턴
