import { LatLng } from './kakaoMap'

export interface Station {
  line: string[]
  stationName: string
  point: LatLng
}
