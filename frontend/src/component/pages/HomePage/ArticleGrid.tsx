import ArticleSmallCard from './ArticleSmallCard'

import { ArticleGridContainer } from '@styled/component/pages/HomePage/ArticleGrid'

type Props = {
  pages: any
}

export default function ArticleGrid({ pages }: Props) {
  return (
    <ArticleGridContainer columns={2} spacing={5}>
      {pages.map(page => {
        return page.data.map(elem => {
          return <ArticleSmallCard data={elem} />
        })
      })}
    </ArticleGridContainer>
  )
}
