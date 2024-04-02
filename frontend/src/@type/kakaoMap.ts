export interface MapLocation {
  placeCategory: string
  centerPoints: LatLng
  limitDist: number
}

export interface LatLng {
  lat: number
  lng: number
}
export interface MapInfo {
  center: LatLng
  level: number
}
