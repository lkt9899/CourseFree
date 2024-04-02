import API_URI from '@constant/url'
import { DateCourse } from '@type/course'
import { MapLocation } from '@type/kakaoMap'

import { authAxios } from '..'

export const requestPlaceInfo = (data: MapLocation) => {
  const response = authAxios.post(`${API_URI.PLACE_INFO}`, data)
  return response
}
export const requestSubmitDateCourse = async (data: DateCourse) => {
  const placeIdArr = data.placeList.map(e => {
    return e.id
  })
  const request = { courseTitle: data.courseTitle, placeIdList: placeIdArr }
  return await authAxios.post(`${API_URI.SUBMIT_DATECOURSE}`, request)
}
