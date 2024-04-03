from typing import Optional

from pydantic import BaseModel

from domain.place import Place
from domain.points import Points


class PlaceDto(BaseModel):
    id: int
    name: str
    address: str
    url: str
    restaurant_type: str
    place_category: str
    points: Points

    class Config:
        arbitrary_types_allowed = True

    @classmethod
    def from_place(cls, place: Place) -> 'PlaceDto':
        return cls(
            id=place.place_id,
            name=place.name,
            address=place.address,
            url=place.url,
            restaurant_type=place.place_type,
            place_category=place.place_category,
            points=Points(place.latitude, place.longitude)
        )
