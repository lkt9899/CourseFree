import { Link } from 'react-router-dom'

import TextBox from '@component/common/TextBox'
import TitleBar from '@component/common/TitleBar'
import FlexBox from '@component/layout/FlexBox'
import Spacing from '@component/layout/Spacing'

export default function SelectMakingWay() {
  return (
    <>
      <TitleBar hasBackPage title="코스 만들기" />
      <FlexBox d="column" h="100%">
        <Link to="./recommend">
          <FlexBox a="center" t="left" j="center">
            <TextBox typography="t1" fontWeight="bold">
              추천 받기
            </TextBox>
          </FlexBox>
        </Link>
        <Spacing size="100px" />
        <Link to="./selfMake">
          <FlexBox a="center" t="left" j="center">
            <TextBox typography="t1" fontWeight="bold">
              직접 만들기
            </TextBox>
          </FlexBox>
        </Link>
      </FlexBox>
    </>
  )
}
