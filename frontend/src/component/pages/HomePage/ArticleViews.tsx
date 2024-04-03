import { ARTICLES_PER_PAGE } from '@constant/pagination'
import { useState } from 'react'

import ArticleFilterNavigation from './ArticleFilterNavigation'
import ArticleGrid from './ArticleGrid'

import FullPageLoading from '@component/common/FullPageLoading'
import Spacing from '@component/layout/Spacing'

import { useSearchArticleQuery } from '@hook/ReactQuery/useSearchArticleQuery'

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

  if (isLoading) return <FullPageLoading />
  console.log(articleCards)
  return (
    <ArticleViewsContainer>
      <button
        onClick={() => {
          console.log(hasNextPage)
          if (hasNextPage) fetchNextPage()
        }}
      >
        button
      </button>
      <ArticleFilterNavigation filter={filter} onClick={onClickFilterHandler} />
      <Spacing size="1rem" />
      <ArticleGrid pages={articleCards.pages} />
    </ArticleViewsContainer>
  )
}
