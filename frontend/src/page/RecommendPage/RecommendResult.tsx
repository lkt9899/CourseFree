import { Link, useLocation } from 'react-router-dom'

import LongCourseInfo from '@component/Course/LongCourseInfo'
import Button from '@component/common/Button'
import FullPageLoading from '@component/common/FullPageLoading'
import RecommendTopBar from '@component/layout/RecommendTopBar'

import { useCourseDetailQuery } from '@hook/ReactQuery/useCourseDetailQuery'

export default function RecommendResult() {
  const location = useLocation()
  const courseId = location.state.courseId
  const { data, isLoading } = useCourseDetailQuery(courseId)

  if (isLoading) return <FullPageLoading />
  return (
    <>
      <RecommendTopBar />
      <LongCourseInfo courseData={data.data} />
      <Link to="../">
        <Button>다른 추천 코스 둘러보기</Button>
      </Link>
    </>
  )
}
