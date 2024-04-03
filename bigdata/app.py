import logging

import uvicorn
from fastapi import FastAPI

from dto.recommend_request_dto import RecommendRequestDto
from service.recommendation_service import make_recommendations

app = FastAPI()

@app.get("/")
async def hi():
    return "Hi"


@app.post("/data/recommendations")
async def search(search_place_dto: RecommendRequestDto):
    return make_recommendations(search_place_dto)


if __name__ == "__main__":
    uvicorn.run("app:app", reload=True, log_config=None)
