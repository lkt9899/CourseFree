import { css } from '@emotion/react'
import { Station } from '@type/course'
import { Link } from 'react-router-dom'

import SubwayLineIcon from './SubwayLineIcon'
import TextBox from './TextBox'

import FlexBox from '@component/layout/FlexBox'

const Container = css`
  padding: 20px;
`
const MOCK: Station = {
  stationName: '역삼',
  line: ['2호선', '경의중앙선'],
  point: { lat: 37.5, lng: 127.03 },
}
/** @jsxImportSource @emotion/react */
export default function SearchResult() {
  return (
    <FlexBox a="center" h="30px" css={Container}>
      <TextBox
        typography="t6"
        onClick={() => {
          sessionStorage.setItem('station', JSON.stringify(MOCK))
        }}
      >
        <Link to="current">역삼</Link>
      </TextBox>
      <SubwayLineIcon line="7">2</SubwayLineIcon>
    </FlexBox>
  )
}
