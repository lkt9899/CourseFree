import kiss from '@asset/kiss.jfif'
import { Avatar, WrapItem } from '@chakra-ui/react'
import styled from '@emotion/styled'

import TextBox from '@component/common/TextBox'
import FlexBox from '@component/layout/FlexBox'

const Container = styled(FlexBox)({
  borderBottom: '1px solid #f3f3f3',
  // borderTop: '1px solid #f3f3f3',
})

export default (function ArticleUserProfile() {
  return (
    <Container>
      <FlexBox a="center" w="100%" p="10px">
        {/* userprofileimage */}
        <WrapItem>
          <Avatar name="Dan Abrahmov" src={kiss} />
        </WrapItem>
        <FlexBox d="column" p="0px 5px 0px 5px">
          <TextBox typography="t7" fontWeight="bold">
            역삼동정현규
          </TextBox>
          <TextBox typography="t7" color="gray">
            공간과 분위기가 주는 낭만을 좋아합니다.
          </TextBox>
        </FlexBox>
        {/* userId and comment */}
      </FlexBox>
    </Container>
  )
})
