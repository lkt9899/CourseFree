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
  imageUrl: string
}

export interface DateCourse {
  courseTitle: string
  placeList: Place[]
}
export interface DateCourseDetail {
  title: string
  placeDtoList: Place[]
  useCount: number
}

export interface RequestPlaceInfo {
  place_category: string
  center_points: LatLng
  limit_dist: number
}
export interface RecommendData {
  points: LatLng
  limitDist: number
  categoryList: Categories[]
}
