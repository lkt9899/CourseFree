import { useLocation } from 'react-router-dom'

import LongCourseInfo from '@component/Course/LongCourseInfo'
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
    </>
  )
}
