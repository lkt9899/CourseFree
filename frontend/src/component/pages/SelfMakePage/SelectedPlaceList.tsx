import { MakingCourseContext } from '@context/index'
import { Place } from '@type/course'
import { useContext } from 'react'

import PlaceInfoWithOrder from '@component/Course/PlaceInfoWithOrder'
import ShortPlaceInfo from '@component/Course/ShortPlaceInfo'
import FlexBox from '@component/layout/FlexBox'

type SelectedPlaceList = {
  placeList: Place[]
  onClickPlaceBox: (place: Place) => void
}
export default (function SelectedPlaceList({
  placeList,
  onClickPlaceBox,
}: SelectedPlaceList) {
  const { dateCourse, setDateCourse } = useContext(MakingCourseContext)
  const removePlace = (place: Place) => {
    const copy = dateCourse.placeList.filter(
      (currPlace: Place) => currPlace.id != place.id,
    )
    setDateCourse({ ...dateCourse, placeList: copy })
  }

  return (
    <FlexBox a="center" d="column">
      {placeList.map((place, i) => {
        return (
          <PlaceInfoWithOrder key={i} order={i + 1}>
            <ShortPlaceInfo
              hasButton
              buttonText="삭제"
              place={place}
              onClickButton={removePlace}
              onClickBox={onClickPlaceBox}
            />
          </PlaceInfoWithOrder>
        )
      })}
    </FlexBox>
  )
})
