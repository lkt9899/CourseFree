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
  const { setDateCourse, dateCourse } = useContext(MakingCourseContext)
  /**
   * 데이트 코스 추가 함수
   */
  function addPlaceInPlan(place: Place) {
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
        buttonText="찜하기"
        onClickButton={addPlaceInPlan}
      />
    </BottomSheet>
  )
}
