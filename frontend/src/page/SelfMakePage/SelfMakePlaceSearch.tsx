import { css } from '@emotion/react'
import { useEffect } from 'react'

import CategorySlider from '@component/kakaoMap/CategorySlider'
import KakaoMap from '@component/kakaoMap/KakaoMap'
import FlexBox from '@component/layout/FlexBox'
import PlaceInfoBottomSheet from '@component/pages/SelfMakePage/PlaceInfoBottomSheet'
import StationTitle from '@component/pages/SelfMakePage/SelfMakePlaceSearch/StationTitle'

import useSearchKakaoMap from '@hook/KakaoMap/useSearchKakaoMap'

import { requestPlaceInfo } from '@api/request/course'

const kakaoMapContainer = css`
  position: relative;
`

/**
 * TODO : 최초 페이지 마운트 시 지하철 역이 중심 좌표가 된다 -> ok
 * TODO : 지도가 움직일 때 마다 중심 좌표는 계속 업데이트 된다
 * TODO : 중심 좌표가 업데이트 될 때 마다 api 호출을 통해 placeList를 받아온다
 * TODO : 마커를 클릭 하면 해당 위치로 중심 좌표가 이동하며 api 호출과 함께 클릭한 마커의 장소 정보가 bottomSheet로 제공된다
 * TODO : bottomSheet는 스크롤을 통해 사용자가 끌 수 있다
 * TODO : 좌표변경 이벤트가 발생할 때 마다 api호출? no 디바운스 기법을 활용해 state 변경 자체를 늦춘다
 */
/** @jsxImportSource @emotion/react */
export default function SelfMakePlaceSearch() {
  const station = JSON.parse(sessionStorage.getItem('station'))
  const {
    markerPlaceList,
    setMarkerPlaceList,
    category,
    onClickCategoryHandler,
    onClickMarkerHandler,
    debounceCenterView,
    hidePlaceInfoSheet,
    currentSelectPlace,
    centerView,
  } = useSearchKakaoMap(station)

  const distMap = {
    1: 50,
    2: 100,
    3: 250,
    4: 400,
    5: 400,
    6: 400,
  }
  // api 호출 조건
  useEffect(() => {
    const initLocation = {
      placeCategory: category,
      centerPoints: centerView.center,
      limitDist: distMap[centerView.level],
    }
    requestPlaceInfo(initLocation)
      .then(({ data }) => {
        const extendedPlaceList = data['placeDtoList']
        setMarkerPlaceList(extendedPlaceList)
      })
      .catch()
  }, [centerView, category])

  return (
    <>
      <FlexBox w="100%" j="center" d="column" a="center">
        <StationTitle name={station.stationName} />

        <FlexBox w="100%" css={kakaoMapContainer}>
          {/* 태그 슬라이더 */}
          <CategorySlider
            onClick={onClickCategoryHandler}
            selectCategory={category}
          />
          {/* 카카오맵 */}

          <KakaoMap
            width="100%"
            height="100vh"
            padding="0px"
            onClickMarkerHandler={onClickMarkerHandler} // 마커 클릭 이벤트에 대한 콜백
            placeList={markerPlaceList} // 장소 조회 api의 응답값
            centerView={centerView} // 지하철역의 좌표가 카카오맵의 중심 좌표가 된다
            setCenterView={debounceCenterView}
            hasMarker
            hasLine={false}
          />
        </FlexBox>
      </FlexBox>
      {currentSelectPlace && (
        <PlaceInfoBottomSheet
          place={currentSelectPlace}
          backDrop={false}
          visibleHandler={hidePlaceInfoSheet}
        />
      )}
    </>
  )
}
