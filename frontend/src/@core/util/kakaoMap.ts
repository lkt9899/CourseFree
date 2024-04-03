import { Place } from '@type/course'

/**
 * @summary 지역 정보를 받아 마커를 표시하는 함수입니다
 * @param map 할당할 지도
 * @param placeList 마커를 표시할 지역의 정보 배열
 * @param onClickMarkerHandler 마커 클릭 이벤트 콜백 함수

 */
export function makeMarker(map, placeList: Place[], onClickMarkerHandler) {
  if (!placeList) return
  const arr = []
  const bounds = new kakao.maps.LatLngBounds()

  for (let i = 0; i < placeList.length; i++) {
    const place = placeList[i]
    const point = new kakao.maps.LatLng(place.points.lat, place.points.lng)
    const marker = new kakao.maps.Marker({
      map: map,
      position: point,
      title: place.name,
      clickable: true,
    })
    kakao.maps.event.addListener(marker, 'click', function () {
      const position = marker.getPosition()
      map.panTo(position)
      onClickMarkerHandler(place)
    })

    marker.setMap(map)
    bounds.extend(point)
    arr.push(marker)
  }

  return { arr, bounds }
}

export function makeLine(map, placeList) {
  const linePath = []
  for (const e of placeList) {
    const { points } = e
    linePath.push(new kakao.maps.LatLng(points.lat, points.lng))
  }

  // 지도에 표시할 선을 생성합니다
  const polyline = new kakao.maps.Polyline({
    path: linePath, // 선을 구성하는 좌표배열 입니다
    strokeWeight: 2, // 선의 두께 입니다
    strokeColor: '#F687B3', // 선의 색깔입니다
    strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
    strokeStyle: 'solid', // 선의 스타일입니다
  })

  // 지도에 선을 표시합니다
  polyline.setMap(map)
  return polyline
}
