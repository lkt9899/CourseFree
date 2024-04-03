# Standard Library
import logging
import time
from math import radians, sin, cos, sqrt, atan2

# ThirdParty Library
import numpy as np

# Local Application/Library
from domain import points
from domain.points import Points
from dto.place_dto import PlaceDto
from sql.query import QueryExecutor
from util.logger import getLogger


# 지구의 반지름 (단위: km)
EARTH_RADIUS = 6371.0

logger = getLogger()


def make_recommendations(search_place_dto):
    start_time = time.time()
    nearby_place_list = find_nearby_place_list(search_place_dto)
    middle_time = time.time()

    logger.info(f"주변 장소 검색 총 시간 : {middle_time - start_time}")
    member_vector = search_place_dto.member_vector
    similarities = []
    for place in nearby_place_list:
        # 문자열 형태의 벡터를 실수(float) 형태의 배열로 변환
        place_vector = np.array([float(x) for x in place.vectors.split(',')], dtype=np.float32)
        similarity = cosine_similarity(member_vector, place_vector)
        similarities.append((similarity, PlaceDto.from_place(place)))

    # ORDER BY 유사도 DESC
    similarities.sort(key=lambda x: x[0], reverse=True)

    # 상위 5개만 선택
    similarities = similarities[:5]

    # 반환 형식을 Dictionary 변환
    similarities_dict = [{"similarity": s[0], "place": s[1]} for s in similarities]
    end_time = time.time()
    logger.info(f"코사인 유사도 계산, 정렬 등 변환 완료 시간 : {end_time - middle_time}")
    return similarities_dict


def find_nearby_place_list(request):
    start_time = time.time()
    in_boundary_place_list = find_place_list_in_boundary(request)
    end_time = time.time()
    logger.info(f"주변 장소 검색 SQL 시간 : {end_time - start_time}")

    start_time = time.time()
    nearby_place_list = []
    for place in in_boundary_place_list:
        dist = get_distance(Points(request.lat, request.lng), Points(place.latitude, place.longitude))
        if dist <= request.limit_dist:
            nearby_place_list.append(place)
    end_time = time.time()
    logger.info(f"거리 계산 시간 : {end_time - start_time}")
    return nearby_place_list


def get_distance(point1, point2):
    # 라디안 변환한 위도 및 경도의 차이 계산
    delta_lat = radians(point2.lat - point1.lat)
    delta_lng = radians(point2.lng - point1.lng)

    # Haversine 거리 계산
    tmp1 = sin(delta_lat / 2) ** 2 \
           + cos(radians(point1.lat)) \
           * cos(radians(point2.lat)) \
           * sin(delta_lng / 2) ** 2
    tmp2 = 2 * atan2(sqrt(tmp1), sqrt(1 - tmp1))
    return points.EARTH_RADIUS * tmp2  # Distance in m


def find_place_list_in_boundary(request):
    center_points = Points(request.lat, request.lng)
    dist = request.limit_dist
    min_points = center_points.get_min_points(dist)
    max_points = center_points.get_max_points(dist)

    query_executor = QueryExecutor()
    return query_executor.find_place_list_by_boundary(
        min_points, max_points, request.place_category)


def cosine_similarity(vector1, vector2):
    # 코사인 유사도 계산
    dot_product = np.dot(vector1, vector2)
    norm1 = np.linalg.norm(vector1)
    norm2 = np.linalg.norm(vector2)
    if norm1 * norm2 == 0:
        return 0
    return dot_product / (norm1 * norm2)


def adjust_user_vector(user_vector, index, value):
    # 사용자 벡터 조정
    user_vector[index] += value
    return user_vector
