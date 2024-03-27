from typing import List, Dict, Union, Any

from pydantic import BaseModel

from dto.place_dto import PlaceDto


class RecommendResponseDto(BaseModel):
    similarity: float
    place: PlaceDto

    @classmethod
    def from_similarities(cls, similarities):
        return [cls(similarity=s[0], place=s[1]) for s in similarities]
