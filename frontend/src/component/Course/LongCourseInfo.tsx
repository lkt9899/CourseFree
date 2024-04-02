import { DateCourseDetail, Place } from '@type/course'

import PlaceInfo from './PlaceInfo'

import TextBox from '@component/common/TextBox'
import FlexBox from '@component/layout/FlexBox'

type Props = {
  courseData: DateCourseDetail
}
export default function LongCourseInfo({ courseData }: Props) {
  return (
    <FlexBox d="column" a="center">
      <TextBox fontWeight="bold" padding="10px" typography="t5">
        {courseData.title}
      </TextBox>
      {courseData.placeDtoList.map((place: Place) => {
        return <PlaceInfo place={place} />
      })}
    </FlexBox>
  )
}
