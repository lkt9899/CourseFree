import { ARTICLES_PER_PAGE } from '@constant/pagination'
import { useState } from 'react'

import ArticleFilterNavigation from './ArticleFilterNavigation'
import ArticleGrid from './ArticleGrid'

import Spacing from '@component/layout/Spacing'

import { useSearchArticleQuery } from '@hook/ReactQuery/useSearchArticleQuery'
import { useObserver } from '@hook/useObserver'

import { debounce } from '@util/debounce'

import { ArticleViewsContainer } from '@styled/component/pages/HomePage/ArticleViews'

export type ArticleFilterType = 'recent' | 'wish-list'

export default function ArticleViews() {
  const [filter, setFilter] = useState<ArticleFilterType>('recent')
  function onClickFilterHandler() {
    if (filter === 'recent') setFilter('wish-list')
    else if (filter === 'wish-list') setFilter('recent')
  }
  const {
    articleCards,
    isLoading,
    // isError,
    fetchNextPage,
    // isFetchingNextPage,
    hasNextPage,
  } = useSearchArticleQuery({
    articlesPerPage: ARTICLES_PER_PAGE,
    filter: filter,
  })

  // 무한스크롤
  const ref = useObserver(async (entry, observer) => {
    observer.unobserve(entry.target)
    const debouceFetchPage = debounce(fetchNextPage, 300)
    if (hasNextPage && !isLoading) {
      debouceFetchPage()
    }
  })

  return (
    <ArticleViewsContainer>
      <ArticleFilterNavigation filter={filter} onClick={onClickFilterHandler} />
      <Spacing size="1rem" />
      {!isLoading && (
        <ArticleGrid
          pages={articleCards.pages}
          ref={ref}
          isLoading={isLoading}
        />
      )}
    </ArticleViewsContainer>
  )
}
