import { Spinner } from '@chakra-ui/react'

import FlexBox from '@component/layout/FlexBox'

import useSocialLogin from '@hook/useSocialLogin'

export default function KakaoLoginPage() {
  useSocialLogin('kakao')

  return (
    <FlexBox w="100%" h="100vh" a="center" j="center">
      <Spinner />
    </FlexBox>
  )
}
