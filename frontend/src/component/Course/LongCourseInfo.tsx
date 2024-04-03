import { DateCourseDetail, Place } from '@type/course'
import { useNavigate } from 'react-router-dom'

import PlaceInfo from './PlaceInfo'

import Button from '@component/common/Button'
import TextBox from '@component/common/TextBox'
import FlexBox from '@component/layout/FlexBox'

type Props = {
  courseData: DateCourseDetail
}
export default function LongCourseInfo({ courseData }: Props) {
  const navigate = useNavigate()
  return (
    <FlexBox d="column" a="center">
      <TextBox fontWeight="bold" padding="10px" typography="t5">
        {courseData.title}
      </TextBox>
      {courseData.placeDtoList.map((place: Place) => {
        return <PlaceInfo place={place} />
      })}

      <Button
        onClick={() => {
          navigate(-1)
        }}
      >
        다른 코스 둘러보기
      </Button>
    </FlexBox>
  )
}
