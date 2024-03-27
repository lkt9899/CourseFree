import logo from '@asset/logo_demo.jpg'
import { Image } from '@chakra-ui/react'
import { SignUpStepContext } from '@context/index'
import { useContext } from 'react'
import { Link } from 'react-router-dom'

import { BottomFixedButtonStyle } from './SetNickName'

import Button from '@component/common/Button'
import TextBox from '@component/common/TextBox'
import FlexBox from '@component/layout/FlexBox'
import Spacing from '@component/layout/Spacing'

/** @jsxImportSource @emotion/react */
export default function Welcome() {
  const { nickname } = useContext(SignUpStepContext)
  return (
    <>
      <FlexBox d="column" a="center">
        <Image src={logo} boxSize="300px" />
        <TextBox typography="t3" fontWeight="bold">
          회원가입 완료!
        </TextBox>
        <Spacing />
        <TextBox typography="t3" fontWeight="bold">
          {nickname}님 환영합니다.
        </TextBox>
      </FlexBox>
      <Link to="/home">
        <Button css={BottomFixedButtonStyle} full>
          홈으로 이동
        </Button>
      </Link>
    </>
  )
}
