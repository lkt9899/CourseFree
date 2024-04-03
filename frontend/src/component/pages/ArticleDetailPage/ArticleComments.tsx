import { css } from '@emotion/react'
import { TComment } from '@type/article'

import Comment from './Comment'
import NoCommentMessage from './NoCommentMessage'

import FlexBox from '@component/layout/FlexBox'

const ArticleCommentBox = css`
  overflow-y: scroll;
  height: 500px;
  padding-bottom: 3rem;
`

type Props = {
  comments: TComment[]
}
/** @jsxImportSource @emotion/react */
export default function ArticleComments({ comments }: Props) {
  return (
    <FlexBox d="column" css={ArticleCommentBox}>
      {comments.length != 0 ? (
        comments.map((comment, i) => <Comment comment={comment} key={i} />)
      ) : (
        <NoCommentMessage />
      )}
    </FlexBox>
  )
}
