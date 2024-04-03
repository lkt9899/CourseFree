import { Spinner } from '@chakra-ui/react'
import { forwardRef } from 'react'

import ArticleSmallCard from './ArticleSmallCard'

import { ArticleGridContainer } from '@styled/component/pages/HomePage/ArticleGrid'

type Props = {
  pages: any
  isLoading: boolean
}

export default forwardRef(function ArticleGrid(
  { pages, isLoading }: Props,
  ref: React.ForwardedRef<HTMLDivElement>,
) {
  return (
    <ArticleGridContainer columns={2} spacing={5}>
      {pages.map(page => {
        return page.data.map(elem => {
          return <ArticleSmallCard data={elem} />
        })
      })}
      {isLoading ? (
        // <FullPageLoading />
        <div>
          <Spinner />
        </div>
      ) : (
        <div ref={ref} style={{ width: '100%', height: '100px' }}></div>
      )}
    </ArticleGridContainer>
  )
})
