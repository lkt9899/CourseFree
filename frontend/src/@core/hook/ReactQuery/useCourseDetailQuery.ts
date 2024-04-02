import QUERY_KEY from '@constant/queryKey'
import { useQuery } from '@tanstack/react-query'

import { requestCourseDetail } from '@api/request/course'

export const useCourseDetailQuery = (id: number) => {
  const { isLoading, isError, data } = useQuery({
    queryKey: [QUERY_KEY.COURSE_DETAIL, id],
    queryFn: () => requestCourseDetail(id),
  })
  return {
    isLoading,
    isError,
    data,
  }
}
