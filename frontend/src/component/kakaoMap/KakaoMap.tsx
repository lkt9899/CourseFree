import { CSSProperties, MutableRefObject, useEffect, useRef } from 'react'

import { makeLine, makeMarker } from '@util/kakaoMap'

interface KakaoMap {
  width: CSSProperties['width']
  height: CSSProperties['height']
  padding?: CSSProperties['padding']
  data?: any
  onClickMarkerHandler?: (data) => void
}
export default function KakaoMap({
  width,
  height,
  padding,
  data,
  onClickMarkerHandler,
}: KakaoMap) {
  const mapRef = useRef<HTMLElement>(null)

  const initMap = () => {
    const container = document.getElementById('map')
    const options = {
      center: new window.kakao.maps.LatLng(33.450701, 126.570667),
      level: 2,
    }
    const map = new window.kakao.maps.Map(container as HTMLElement, options)
    ;(mapRef as MutableRefObject<any>).current = map

    // 마커 표시
    makeMarker(map, data, onClickMarkerHandler)
    // 선 표시 (search 페이지에서만 표시)
    makeLine(map)
  }

  useEffect(() => {
    window.kakao.maps.load(() => initMap())
  }, [mapRef])

  return (
    <div
      id="map"
      style={{ width: width, height: height, padding: padding }}
    ></div>
  )
}
