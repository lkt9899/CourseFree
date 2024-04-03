import QUERY_KEY from '@constant/queryKey'
import { useQuery } from '@tanstack/react-query'

import { requestArticleComment } from '@api/request/article'

export const useArticleCommentQuery = (id: number) => {
  const { data, isLoading, isError } = useQuery({
    queryKey: [QUERY_KEY.ARTICLE_COMMENT, id],
    queryFn: () => requestArticleComment(id),
    select: data => data.data,
  })
  return { data, isLoading, isError }
}
