import { Image } from '@chakra-ui/react'
import { PostContentInfo } from '@type/article'

import TextBox from '@component/common/TextBox'
import FlexBox from '@component/layout/FlexBox'

type Props = {
  postContentInfo: PostContentInfo
}
export default (function ArticleInfoByPlace({ postContentInfo }: Props) {
  return (
    <>
      <FlexBox d="column" w={'100%'}>
        {/* image */}
        <Image
          src={postContentInfo.placeImageUrl}
          width={'100%'}
          height={'300px'}
        />
        {/* content */}
        <TextBox
          textAlign="left"
          fontWeight="bold"
          typography="t4"
          padding={'10px 10px'}
        >
          {postContentInfo.title}
        </TextBox>
        <TextBox
          textAlign="left"
          typography="t5"
          color="gray"
          padding={'0px 10px'}
          display="flex"
        >
          {postContentInfo.content}
        </TextBox>
      </FlexBox>
    </>
  )
})
