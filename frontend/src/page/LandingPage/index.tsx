import CourseFree from '@asset/CourseFree.png'
import Flower from '@asset/Flower.json'
import { Image } from '@chakra-ui/react'
import Lottie from 'lottie-react'

import Button from '@component/common/Button'
import TextBox from '@component/common/TextBox'
import FlexBox from '@component/layout/FlexBox'

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
      <Lottie
        animationData={Flower}
        style={{
          position: 'absolute',
          top: '0',
        }}
      ></Lottie>
      <FlexBox d="column" p="1.2rem" a="center" j="space-between" h="100vh">
        {/* Header */}
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
        <FlexBox d="column" a="center">
          <TextBox fontWeight="bold">성향 기반 데이트 코스 추천 서비스</TextBox>
          <Image src={CourseFree} />
        </FlexBox>

        <Button color="black" bgColor="yellow" full onClick={handleLogin}>
          카카오로 시작하기
        </Button>
      </FlexBox>
    </>
  )
}
