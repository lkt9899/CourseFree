from domain.place import Place
from sql.database import EngineConn


class QueryExecutor:
    def __init__(self):
        self.db = EngineConn()

    def find_place_list_by_boundary(self, min_points, max_points, place_category):
        session = self.db.session_maker()
        try:
            result = session.query(Place).filter(
                Place.latitude.between(min(min_points.lat, max_points.lat), max(min_points.lat, max_points.lat)),
                Place.longitude.between(min(min_points.lng, max_points.lng), max(min_points.lng, max_points.lng)),
                Place.place_category == place_category
            ).all()
            return result
        finally:
            session.close()


