from sqlalchemy import Column, Integer, String, Float
from sqlalchemy.ext.declarative import declarative_base

Base = declarative_base()


class Place(Base):
    __tablename__ = "place"

    place_id = Column(Integer, nullable=False, autoincrement=True, primary_key=True)
    name = Column(String, nullable=False)
    address = Column(String, nullable=False)
    url = Column(String)
    place_type = Column(String)
    vectors = Column(String)
    place_category = Column(String)
    latitude = Column(Float)
    longitude = Column(Float)
