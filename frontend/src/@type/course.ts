import { Categories } from '@data/category'

import { LatLng } from './kakaoMap'

export interface Place {
  id: number
  name: string
  address: string
  url: string
  placeCategory: Categories
  placeType: string
  points: LatLng
}

export interface DateCourse {
  courseTitle: string
  placeList: Place[]
}
export interface Station {
  line: string[]
  stationName: string
  point: LatLng
}

export interface RequestPlaceInfo {
  place_category: string
  center_points: LatLng
  limit_dist: number
}
