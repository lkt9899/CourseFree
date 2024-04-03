import QUERY_KEY from '@constant/queryKey'
import { queryClient } from '@data/queryClient'
import { useMutation } from '@tanstack/react-query'

import { requestAddFavoriteCourse } from '@api/request/course'

export const useAddFavoriteCourseQuery = (courseId: number) => {
  // 업데이트할 쿼리

  const mutation = useMutation({
    mutationFn: () => {
      return requestAddFavoriteCourse(courseId)
    },

    onSettled: () => {
      return queryClient.invalidateQueries({
        queryKey: [QUERY_KEY.ARTICLE_DETAIL],
      })
    },
  })

  return mutation
}
