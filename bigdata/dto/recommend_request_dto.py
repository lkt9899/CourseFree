from typing import List
from pydantic import BaseModel


class RecommendRequestDto(BaseModel):
    member_vector: List[float]
    place_category: str
    lat: float
    lng: float
    limit_dist: float
