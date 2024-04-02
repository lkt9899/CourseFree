import { Spinner } from '@chakra-ui/react'

import FlexBox from '@component/layout/FlexBox'
import Spacing from '@component/layout/Spacing'

export default function FullPageLoading() {
  return (
    <FlexBox w="100%" h="100%" a="center" j="center" d="column">
      <Spinner color="hotpink" />
      <Spacing />
      조금만 기다려주세요!
    </FlexBox>
  )
}
