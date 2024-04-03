import { css } from '@emotion/react'

import FlexBox from './FlexBox'

const Container = css({ borderBottom: '2px solid var(--pink300)' })
/** @jsxImportSource @emotion/react */
export default function RecommendTopBar() {
  return (
    <FlexBox d="column" css={Container}>
      <FlexBox t="right" display="block" p="10px">
        {/* <TextBox fontWeight="bold" typography="t6">
          코스 찜하기💙
        </TextBox> */}
      </FlexBox>
    </FlexBox>
  )
}
