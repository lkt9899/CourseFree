# 베이스 이미지 설정 (Python 3.8 기반의 공식 이미지 사용)
FROM python:3.6.8

WORKDIR /app

COPY bigdata/. .

RUN python -m pip install --upgrade pip --user

# 필요한 패키지 설치
RUN pip install -r requirements.txt

RUN pip install "uvicorn[standard]"

EXPOSE 8000

# 컨테이너 실행 명령 설정 (uvicorn으로 FastAPI 앱 실행)
CMD ["uvicorn", "app:app", "--host", "0.0.0.0", "--port", "8000"]
