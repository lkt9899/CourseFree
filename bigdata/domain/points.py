import math

EARTH_RADIUS = 6371000.0


class Points:
    def __init__(self, lat, lng):
        self.lat = lat
        self.lng = lng

    def calculate_points(self, dist, isMax):
        delta_lat = math.degrees(dist / EARTH_RADIUS)
        delta_lon = math.degrees(dist / (EARTH_RADIUS * math.cos(math.radians(self.lat))))
        new_lat = self.lat + (delta_lat if isMax else -delta_lat)
        new_lon = self.lng + (delta_lon if isMax else -delta_lon)
        return Points(new_lat, new_lon)

    def get_min_points(self, dist):
        return self.calculate_points(dist, False)

    def get_max_points(self, dist):
        return self.calculate_points(dist, True)

