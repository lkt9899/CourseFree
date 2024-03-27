import { css } from '@emotion/react'
import { Link } from 'react-router-dom'

import Button from '@component/common/Button'
import Input from '@component/common/Input'
import KakaoMap from '@component/kakaoMap/KakaoMap'
import TextBox from '@component/common/TextBox'
import KakaoMap from '@component/kakaoMap/KakaoMap'
import FlexBox from '@component/layout/FlexBox'
import Spacing from '@component/layout/Spacing'
import SelectedPlaceList from '@component/pages/SelfMakePage/SelectedPlaceList'

import useMeasure from '@hook/useMeasure'

const StickyMap = css`
  position: sticky;
  top: 0px;
  /* border-radius: 10px;
  overflow: hidden; */
`

/** @jsxImportSource @emotion/react */
export default function SelfMakeCurrent() {
  // 현재 포커싱된 place에 따라 카카오맵의 중심 이동
  // place focusing이 바뀌는 타이밍은 observer로 관찰
  // observer의 범위, 리스트에 대한 observer등록

  const { widthState, ref: measureRef } = useMeasure()
  return (
    <>
      <FlexBox j="center" d="column" p="10px" w="100%" ref={measureRef}>
        <TextBox typography="t2">역삼</TextBox>
        <FlexBox a="center" j="space-between">
          <TextBox fontWeight="bold">
            코스 이름 :
            <Input
              placeholder="생성할 코스 이름을 입력해주세요"
              width={'220px'}
            />
          </TextBox>

          <Button bgColor="pink400">코스 완성</Button>
        </FlexBox>
        <Button
          css={css`
            position: fixed;
            bottom: 5rem;
            width: ${widthState - 20}px;
          `}
        >
          <Link to="../search">장소 추가하기</Link>
        </Button>
      </FlexBox>
      <FlexBox css={StickyMap}>
        <KakaoMap width="100%" height="200px" />
      </FlexBox>
      <Spacing size="7px" />
      <SelectedPlaceList />
    </>
  )
}
