import { Spinner } from '@chakra-ui/react'
import { ARTICLES_PER_PAGE } from '@constant/pagination'
import { useMemo } from 'react'

import ArticleSmallCard from './ArticleSmallCard'
import { ArticleFilterType } from './ArticleViews'

import { useSearchArticleQuery } from '@hook/ReactQuery/useSearchArticleQuery'
import { useObserver } from '@hook/useObserver'

import { ArticleGridContainer } from '@styled/component/pages/HomePage/ArticleGrid'

type Props = {
  filter: ArticleFilterType
}

export default function ArticleGrid({ filter }: Props) {
  const {
    articleCards,
    isFetching,
    // isError,
    fetchNextPage,
    // isFetchingNextPage,
    hasNextPage,
  } = useSearchArticleQuery({
    articlesPerPage: ARTICLES_PER_PAGE,
    filter: filter,
  })
  const articleList = useMemo(
    () =>
      articleCards
        ? articleCards.pages.flatMap(({ data }) => {
            return data
          })
        : [],
    [articleCards],
  )

  // 무한스크롤
  const ref = useObserver(
    async (entry, observer) => {
      observer.unobserve(entry.target)
      // const debouceFetchPage = debounce(fetchNextPage, 300)
      if (hasNextPage && !isFetching) {
        fetchNextPage()
      }
    },
    { threshold: 1 },
  )
  return (
    <>
      <ArticleGridContainer columns={2} spacing={5}>
        {articleList.map(e => {
          return <ArticleSmallCard data={e} />
        })}
        {isFetching && <Spinner />}
        <div
          ref={ref}
          style={{ width: '100%', height: '50px', backgroundColor: 'white' }}
        ></div>
      </ArticleGridContainer>
    </>
  )
}
