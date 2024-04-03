import { Categories } from '@data/category'
import { Place } from '@type/course'
import { MapInfo } from '@type/kakaoMap'
import { Station } from '@type/subway'
import { useState } from 'react'

import { debounce } from '@util/debounce'

export default function useSearchKakaoMap(station: Station) {
  const [markerPlaceList, setMarkerPlaceList] = useState<Place[]>([])
  const [category, setCategory] = useState<string>('RESTAURANT')
  const [currentSelectPlace, setCurrentSelectPlace] = useState<Place | null>(
    null,
  )
  const [centerView, setCenterView] = useState<MapInfo>({
    center: {
      lat: station.point.lat,
      lng: station.point.lng,
    },
    level: 2,
  })
  const debounceCenterView = debounce(setCenterView, 200)
  const hidePlaceInfoSheet = () => {
    setCurrentSelectPlace(null)
  }
  // 장소 클릭 시 장소의 세부 정보를 BottomSheet 형태로 나타냅니다
  function onClickMarkerHandler(place: Place) {
    setCurrentSelectPlace(place)
  }
  function onClickCategoryHandler(type: Categories) {
    setCategory(type)
  }
  return {
    markerPlaceList,
    setMarkerPlaceList,
    category,
    onClickCategoryHandler,
    onClickMarkerHandler,
    debounceCenterView,
    hidePlaceInfoSheet,
    currentSelectPlace,
    centerView,
  }
}
