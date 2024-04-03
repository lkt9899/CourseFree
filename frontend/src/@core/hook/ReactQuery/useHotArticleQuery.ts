import QUERY_KEY from '@constant/queryKey'
import { useQuery } from '@tanstack/react-query'

import { requestHotArticle } from '@api/request/article'

export const useHotArticleQuery = () => {
  const {
    isLoading,
    isError,
    data: articleThumbnails,
  } = useQuery({
    queryKey: [QUERY_KEY.HOT_ARTICLE],
    queryFn: requestHotArticle,
  })
  return {
    isLoading,
    isError,
    articleThumbnails,
  }
}
