import { css } from '@emotion/react'

import FlexBox from '@component/layout/FlexBox'

import { subwayColorMap } from '@style/subwayColorMap'

const Container = css`
  border-radius: 20px;
  font-size: 12px;
  padding: 10px;
  height: 25px;
  color: white;
  margin-left: 5px;
`
type Props = {
  children: string
  line: string
}

/** @jsxImportSource @emotion/react */
export default function SubwayLineIcon({ children, line }: Props) {
  return (
    <FlexBox
      a="center"
      j="center"
      css={Container}
      style={{ backgroundColor: subwayColorMap[line] }}
    >
      {children}
    </FlexBox>
  )
}
