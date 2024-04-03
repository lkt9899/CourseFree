import BottomSheet from '@component/layout/BottomSheet'
import FlexBox from '@component/layout/FlexBox'
import ArticleComments from '@component/pages/ArticleDetailPage/ArticleComments'
import CommentInput from '@component/pages/ArticleDetailPage/CommentInput'

interface ArticleCommentSheet {
  handler: any
}
export default function ArticleCommentSheet({ handler }: ArticleCommentSheet) {
  return (
    <BottomSheet title="댓글" visibleHandler={handler} backDrop>
      <FlexBox d="column" h="90%">
        <ArticleComments />
        <CommentInput />
      </FlexBox>
    </BottomSheet>
  )
}
