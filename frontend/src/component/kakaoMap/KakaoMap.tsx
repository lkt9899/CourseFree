import { Place } from '@type/course'
import { MapInfo } from '@type/kakaoMap'
import { CSSProperties, useEffect, useRef, useState } from 'react'

import { makeLine, makeMarker } from '@util/kakaoMap'

interface KakaoMap {
  width: CSSProperties['width']
  height: CSSProperties['height']
  padding: CSSProperties['padding']
  hasLine: boolean
  hasMarker: boolean
  onClickMarkerHandler: any
  placeList: Place[]
  setCenterView?: any
  centerView?: MapInfo
}

/**
 * @summary : kakaoMap Props
 * @param width : 지도의 가로 길이
 * @param height : 지도의 세로 길이
 * @param padding : 지도의 padding
 * @param placeList : 마커를 띄울 장소들에 대한 정보 리스트
 * @param center : 최초 마운트 시 중심 좌표
 * @param hasLine : 장소와 장소를 잇는 선을 표시할 것인가?
 * @param hasMarker : 장소를 표시하는 마커를 띄우는가?
 * @param centerView : 지도의 중심,레벨 데이터
 * @param setCenterView : mapInfo의 debounce setter함수
 */
export default function KakaoMap({
  width,
  height,
  padding,
  onClickMarkerHandler,
  placeList,
  centerView,
  hasLine,
  hasMarker,
  setCenterView = null,
}: KakaoMap) {
  const [kakaoMap, setKakaoMap] = useState(null)
  const [markers, setMarkers] = useState([])
  const container = useRef(null)

  function updateMapInfo(map) {
    const latlng = map.getCenter()
    const level = map.getLevel()

    setCenterView({
      center: { lat: latlng.getLat(), lng: latlng.getLng() },
      level: level,
    })
  }
  const initMap = container => {
    const options = {
      center: new window.kakao.maps.LatLng(
        centerView.center.lat,
        centerView.center.lng,
      ),
      level: centerView.level,
    }
    const map = new window.kakao.maps.Map(container.current, options)
    setKakaoMap(map)

    // 카카오맵에 중심좌표 변경 감지 이벤트 등록
    if (setCenterView)
      kakao.maps.event.addListener(map, 'center_changed', () =>
        updateMapInfo(map),
      )
  }

  // 카카오맵 최초 생성
  useEffect(() => {
    window.kakao.maps.load(() => initMap(container))
  }, [container])
  useEffect(() => {
    if (!kakaoMap) return
    const position = new kakao.maps.LatLng(
      centerView.center.lat,
      centerView.center.lng,
    )
    kakaoMap.panTo(position)
  }, [centerView])
  useEffect(() => {
    markers.forEach(marker => {
      marker.setMap(null)
    })
    // 마커 표시
    if (hasMarker && placeList.length) {
      const arr = makeMarker(kakaoMap, placeList, onClickMarkerHandler)
      setMarkers(arr)
    }
    // 선 표시
    if (hasLine && placeList.length) {
      makeLine(kakaoMap, placeList)
    }
  }, [kakaoMap, placeList])

  return (
    <div
      id="map"
      style={{ width: width, height: height, padding: padding }}
      ref={container}
    ></div>
  )
}
