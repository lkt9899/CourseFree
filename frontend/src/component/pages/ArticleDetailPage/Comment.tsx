import { Avatar } from '@chakra-ui/react'
import { TComment } from '@type/article'

import TextBox from '@component/common/TextBox'
import FlexBox from '@component/layout/FlexBox'

type Props = {
  comment: TComment
}
export default function Comment({ comment }: Props) {
  return (
    <div>
      <FlexBox w="100%" p="10px">
        {/* userprofileimage */}
        <FlexBox d="column">
          <Avatar name="Dan Abrahmov" size="sm" src={comment.memberImageUrl} />
        </FlexBox>
        {/* userName */}
        <FlexBox d="column" p={'0px 5px'}>
          <TextBox typography="t8" fontWeight="bold">
            {comment.memberNickname}
          </TextBox>
          <TextBox typography="t8">{comment.content}</TextBox>
        </FlexBox>

        {/* userId and comment */}
      </FlexBox>
    </div>
  )
}
