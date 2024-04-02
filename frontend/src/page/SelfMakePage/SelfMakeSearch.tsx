import { useEffect } from 'react'

import SearchAutoComplete from '@component/common/SearchAutoComplete'
import SearchBar from '@component/common/SearchBar'

import { useStationAutoCompleteQuery } from '@hook/ReactQuery/useStationAutoCompleteQuery'
import useInput from '@hook/useInput'

export default function SelfMakeSearch() {
  const { state: searchText, debounceChange: onChange } = useInput<string>({
    data: '',
    setDebounce: true,
  })

  const { stationList } = useStationAutoCompleteQuery(searchText)

  return (
    <>
      <SearchBar placeholder="지하철 역을 입력해주세요." onChange={onChange} />

      {stationList && (
        <SearchAutoComplete stationList={stationList.data} type="self" />
      )}
    </>
  )
}
