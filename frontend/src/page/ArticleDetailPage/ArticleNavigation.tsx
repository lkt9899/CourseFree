import styled from '@emotion/styled'
import { PostContentInfo } from '@type/article'

import FlexBox from '@component/layout/FlexBox'
import PlaceAvatar from '@component/pages/ArticleDetailPage/PlaceAvatar'

const ArticleNavigationContainer = styled.div`
  position: sticky;
  top: 0;
  background-color: white;
  border-bottom-left-radius: 5px;
  border-bottom-right-radius: 5px;
`
type Props = {
  postContentInfoList: PostContentInfo[]
  onClick: (idx: number) => void
  currentPlaceIdx: number
}
export default function ArticleNavigation({
  postContentInfoList,
  onClick,
  currentPlaceIdx,
}: Props) {
  return (
    <ArticleNavigationContainer>
      <FlexBox w="100%" a="center" p={'5px 5px'}>
        {/* <PlaceAvatar isSelected />
        <PlaceAvatar />
        <PlaceAvatar />
        <PlaceAvatar />
        <PlaceAvatar isLast /> */}
        {postContentInfoList.map((post, i) => {
          return (
            <PlaceAvatar
              idx={i}
              isSelected={currentPlaceIdx === i}
              src={post.placeImageUrl}
              placeName={post.placeName}
              isLast={i === postContentInfoList.length - 1}
              onClick={onClick}
            />
          )
        })}
      </FlexBox>
    </ArticleNavigationContainer>
  )
}
