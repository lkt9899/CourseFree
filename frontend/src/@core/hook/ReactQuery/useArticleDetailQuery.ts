import QUERY_KEY from '@constant/queryKey'
import { useQuery } from '@tanstack/react-query'

import { requestArticleDetail } from '@api/request/article'

export const useArticleDetailQuery = (id: number) => {
  const { isLoading, isError, data } = useQuery({
    queryKey: [QUERY_KEY.ARTICLE_DETAIL, id],
    queryFn: () => requestArticleDetail(id),
    select: data => data.data,
  })
  return {
    isLoading,
    isError,
    data,
  }
}
