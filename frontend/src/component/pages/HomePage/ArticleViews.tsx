import { useState } from 'react'

import ArticleFilterNavigation from './ArticleFilterNavigation'
import ArticleGrid from './ArticleGrid'

import Spacing from '@component/layout/Spacing'

import { ArticleViewsContainer } from '@styled/component/pages/HomePage/ArticleViews'

export type ArticleFilterType = 'recent' | 'wish-list'

export default function ArticleViews() {
  const [filter, setFilter] = useState<ArticleFilterType>('recent')
  function onClickFilterHandler() {
    if (filter === 'recent') setFilter('wish-list')
    else if (filter === 'wish-list') setFilter('recent')
  }

  return (
    <ArticleViewsContainer>
      <ArticleFilterNavigation filter={filter} onClick={onClickFilterHandler} />
      <Spacing size="1rem" />
      <ArticleGrid filter={filter} />)
    </ArticleViewsContainer>
  )
}
