import { useState } from 'react'
import { useParams } from 'react-router-dom'

import ArticleCommentSheet from './ArticleCommentSheet'
import ArticleFooter from './ArticleFooter'
import ArticleNavigation from './ArticleNavigation'
import ArticleTitle from './ArticleTitle'

import ArticleInfoByPlace from '@page/ArticleDetailPage/ArticleInfoByPlace'
import ArticleUserProfile from '@page/ArticleDetailPage/ArticleUserProfile'

import FullPageLoading from '@component/common/FullPageLoading'
import Section from '@component/layout/Section'

import { useArticleDetailQuery } from '@hook/ReactQuery/useArticleDetailQuery'

export default function ArticleDetailPage() {
  const [placeIdx, setPlaceIdx] = useState(0)
  const { id } = useParams()
  const [bottomSheetState, setBottomSheetState] = useState(false)
  const { data, isLoading } = useArticleDetailQuery(parseInt(id))

  function changePlaceIdx(idx: number) {
    setPlaceIdx(idx)
  }
  function bottomSheetHandler() {
    setBottomSheetState(!bottomSheetState)
  }
  if (isLoading) return <FullPageLoading />

  return (
    <>
      <Section>
        {/* titlerBar */}
        <ArticleTitle title={data.postTitle} />
        {/* userProfile */}
        <ArticleUserProfile
          memberImage={data.memberImageUrl}
          memberName={data.memberNickname}
        />
        {/* place Navigation */}
        <ArticleNavigation
          postContentInfoList={data.postContentInfoList}
          onClick={changePlaceIdx}
          currentPlaceIdx={placeIdx}
        />
        {/* 게시물 정보 */}
        <ArticleInfoByPlace
          postContentInfo={data.postContentInfoList[placeIdx]}
        />
      </Section>
      <ArticleFooter onClick={bottomSheetHandler} />
      {/* BottomSheet */}
      {bottomSheetState && <ArticleCommentSheet handler={bottomSheetHandler} />}
    </>
  )
}
