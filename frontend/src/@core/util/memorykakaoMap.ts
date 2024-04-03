import logo from '@asset/logo_demo.jpg'

export function makeMarker(map, data, onClickMarkerHandler) {
  const imageSrc = logo

  for (let i = 0; i < data.length; i++) {
    // 마커 이미지의 이미지 크기 입니다
    const imageSize = new kakao.maps.Size(24, 35)

    // 마커 이미지를 생성합니다
    const markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize)

    // 마커를 생성합니다
    const marker = new kakao.maps.Marker({
      map: map, // 마커를 표시할 지도
      position: data[i].latlng, // 마커를 표시할 위치
      title: data[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
      image: markerImage, // 마커 이미지
      clickable: true,
    })
    kakao.maps.event.addListener(marker, 'click', function () {
      onClickMarkerHandler(data[i])
    })
    marker.setMap(map)
  }
}

export function makeLine(map) {
  const linePath = [
    new kakao.maps.LatLng(33.450705, 126.570677),
    new kakao.maps.LatLng(33.450936, 126.569477),
    new kakao.maps.LatLng(33.450879, 126.56994),
    new kakao.maps.LatLng(33.451393, 126.570738),
  ]

  // 지도에 표시할 선을 생성합니다
  const polyline = new kakao.maps.Polyline({
    path: linePath, // 선을 구성하는 좌표배열 입니다
    strokeWeight: 5, // 선의 두께 입니다
    strokeColor: '#FFAE00', // 선의 색깔입니다
    strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
    strokeStyle: 'solid', // 선의 스타일입니다
  })

  // 지도에 선을 표시합니다
  polyline.setMap(map)
}
