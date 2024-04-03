import { MakingCourseContext } from '@context/index'
import { Place } from '@type/course'
import { useContext } from 'react'

import ShortPlaceInfo from '@component/Course/ShortPlaceInfo'
import BottomSheet from '@component/layout/BottomSheet'

type Props = {
  backDrop: boolean
  visibleHandler: () => void
  place: Place
}

export default function PlaceInfoBottomSheet({
  place,
  backDrop,
  visibleHandler,
}: Props) {
  const { setDateCourse, dateCourse, selectPlaceSet, setSelectPlaceSet } =
    useContext(MakingCourseContext)
  /**
   * 데이트 코스 추가 함수
   */
  function addPlaceInPlan(place: Place) {
    if (selectPlaceSet.has(place.name)) {
      alert('이미 등록한 장소입니다.')
      return
    }
    const copy = new Set(selectPlaceSet)
    copy.add(place.name)
    setSelectPlaceSet(copy)
    setDateCourse({
      ...dateCourse,
      placeList: [...dateCourse.placeList, place],
    })
  }
  return (
    <BottomSheet
      title={place.name}
      backDrop={backDrop}
      visibleHandler={visibleHandler}
    >
      <ShortPlaceInfo
        place={place}
        hasButton={true}
        buttonText="장소 등록"
        onClickButton={() => {
          addPlaceInPlan(place)
          visibleHandler()
        }}
      />
    </BottomSheet>
  )
}
