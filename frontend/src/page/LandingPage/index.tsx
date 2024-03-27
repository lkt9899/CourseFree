import logo from '@asset/logo_demo.jpg'
import { Image } from '@chakra-ui/react'

import Button from '@component/common/Button'
import TextBox from '@component/common/TextBox'
import FlexBox from '@component/layout/FlexBox'
import Spacing from '@component/layout/Spacing'

/** @jsxImportSource @emotion/react */
export default function LandingPage() {
  const REST_API_KEY = import.meta.env.VITE_KAKAO_REST_API_KEY
  const REDIRECT_URI = import.meta.env.VITE_KAKAO_REDIRECT_URI
  // oauth 요청 URL
  const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code`

  const handleLogin = () => {
    window.location.href = KAKAO_AUTH_URL
  }
  return (
    <>
      <FlexBox d="column" p="1.2rem" j="center" a="center">
        <FlexBox w="100%" d="column">
          <TextBox typography="t2" fontWeight="bold">
            데이트는
          </TextBox>
          <TextBox typography="t2" fontWeight="bold">
            <TextBox typography="t2" fontWeight="bold" color="primary">
              코스프리
            </TextBox>
            와 함께.
          </TextBox>
        </FlexBox>
        <Image src={logo} boxSize="300px" margin={'auto'} />
        <TextBox typography="t1" fontWeight="bold" color="pink500">
          CourseFree.
        </TextBox>

        <Spacing size="150px" />
        <Button color="black" bgColor="yellow" full onClick={handleLogin}>
          카카오로 시작하기
        </Button>
      </FlexBox>
    </>
  )
}
