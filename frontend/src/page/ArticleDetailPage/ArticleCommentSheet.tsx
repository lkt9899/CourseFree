import FullPageLoading from '@component/common/FullPageLoading'
import BottomSheet from '@component/layout/BottomSheet'
import FlexBox from '@component/layout/FlexBox'
import ArticleComments from '@component/pages/ArticleDetailPage/ArticleComments'
import CommentInput from '@component/pages/ArticleDetailPage/CommentInput'

import { useAddCommentQuery } from '@hook/ReactQuery/useAddCommentQuery'
import { useArticleCommentQuery } from '@hook/ReactQuery/useArticleCommentQuery'

interface ArticleCommentSheet {
  handler: any
  postId: number
}
export default function ArticleCommentSheet({
  handler,
  postId,
}: ArticleCommentSheet) {
  // api 요청
  const { data, isLoading } = useArticleCommentQuery(postId)
  const { mutate } = useAddCommentQuery()

  if (isLoading) return <FullPageLoading />

  return (
    <BottomSheet title="댓글" visibleHandler={handler} backDrop>
      <FlexBox d="column">
        <ArticleComments comments={data} />
        <CommentInput mutate={mutate} />
      </FlexBox>
    </BottomSheet>
  )
}
