import { css } from '@emotion/react'
import { Station } from '@type/subway'

import SearchResult from './SearchResult'

import FlexBox from '@component/layout/FlexBox'

import { filter } from '@util/Subway/filterStation'

const Container = css`
  border-bottom: 1px solid #f3f3f3;
  padding: 5px;
  border-bottom-left-radius: 10px;
  border-bottom-right-radius: 10px;
`
type Props = {
  stationList: Station[]
  type: 'self' | 'recommend'
  onClick?: (station: Station) => void
}
/** @jsxImportSource @emotion/react */
export default function SearchAutoComplete({
  stationList,
  type,
  onClick,
}: Props) {
  const filteredStationList = filter(stationList)

  return (
    <FlexBox d="column" w="100%" css={Container} bgColor="grey">
      {filteredStationList.map((station, i) => {
        if (type === 'self')
          return <SearchResult key={i} data={station} type={type} />
        else if (type === 'recommend')
          return <SearchResult data={station} type={type} onClick={onClick} />
      })}
    </FlexBox>
  )
}
