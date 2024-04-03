import QUERY_KEY from '@constant/queryKey'
import { useInfiniteQuery } from '@tanstack/react-query'

import { ArticleFilterType } from '@component/pages/HomePage/ArticleViews'

import { requestArticles } from '@api/request/article'

interface Props {
  articlesPerPage: number
  filter: ArticleFilterType
}

export const useSearchArticleQuery = ({ articlesPerPage, filter }: Props) => {
  const {
    data: articleCards,
    isFetching,
    isError,
    fetchNextPage,
    isFetchingNextPage,
    hasNextPage,
  } = useInfiniteQuery({
    queryKey: [QUERY_KEY.SEARCH_ARTICLE, filter],
    queryFn: ({ pageParam }) => requestArticles(pageParam, filter),
    initialPageParam: 0,
    getNextPageParam: (lastPage, allPages) => {
      const nextPage = allPages.length + 1
      return lastPage.data.length === 0 ||
        lastPage.data.length < articlesPerPage
        ? undefined
        : nextPage
    },
  })
  return {
    articleCards,
    isFetching,
    isError,
    fetchNextPage,
    isFetchingNextPage,
    hasNextPage,
  }
}
