import { css } from '@emotion/react'
import { Station } from '@type/subway'
import { Link } from 'react-router-dom'

import SubwayLineIcon from './SubwayLineIcon'
import TextBox from './TextBox'

import FlexBox from '@component/layout/FlexBox'

const Container = css`
  padding: 20px;
`
type Props = {
  type: 'self' | 'recommend'
  data: Station
  onClick?: (station: Station) => void
}
/** @jsxImportSource @emotion/react */
export default function SearchResult({ data, type, onClick }: Props) {
  if (type === 'self')
    return (
      <FlexBox a="center" h="30px" css={Container}>
        <TextBox
          typography="t6"
          onClick={() => {
            sessionStorage.setItem('station', JSON.stringify(data))
          }}
        >
          <Link to="current">{data.stationName}</Link>
        </TextBox>
        {data.line.map(metroLine => {
          return <SubwayLineIcon line={metroLine}>{metroLine}</SubwayLineIcon>
        })}
      </FlexBox>
    )
  else if (type == 'recommend')
    return (
      <FlexBox a="center" h="30px" css={Container}>
        <TextBox
          typography="t6"
          onClick={() => {
            // post 요청. 성공 시 화면 전환 후 받은 데이터를 페이지에 뿌려준다.
            onClick(data)
          }}
        >
          <button>{data.stationName}</button>
        </TextBox>
        {data.line.map(metroLine => {
          return <SubwayLineIcon line={metroLine}>{metroLine}</SubwayLineIcon>
        })}
      </FlexBox>
    )
}
