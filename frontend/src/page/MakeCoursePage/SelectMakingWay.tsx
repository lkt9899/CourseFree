import { Link } from 'react-router-dom'

import Button from '@component/common/Button'
import TextBox from '@component/common/TextBox'
import TitleBar from '@component/common/TitleBar'
import FlexBox from '@component/layout/FlexBox'
import Spacing from '@component/layout/Spacing'

export default function SelectMakingWay() {
  return (
    <>
      <TitleBar hasBackPage title="코스 만들기" />
      <FlexBox w="100%" a="center" j="space-evenly">
        <Link to="./recommend">
          <FlexBox a="center" t="left" j="center" d="column">
            <Button>
              <TextBox typography="t4" fontWeight="bold" color="white">
                추천 받기
              </TextBox>
            </Button>
            <Spacing />
            <TextBox typography="t8">
              사용자의 성향을 고려한 맞춤 코스입니다.
            </TextBox>
          </FlexBox>
        </Link>
        <Spacing size="100px" />
        <Link to="./selfMake">
          <FlexBox a="center" t="left" j="center" d="column">
            <Button>
              <TextBox typography="t4" fontWeight="bold" color="white">
                직접 제작하기
              </TextBox>
            </Button>
            <Spacing />
            <TextBox typography="t8">장소를 직접 골라 계획을 짜보아요!</TextBox>
          </FlexBox>
        </Link>
      </FlexBox>
    </>
  )
}
