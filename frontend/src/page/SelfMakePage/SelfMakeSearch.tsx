import { useEffect } from 'react'

import SearchAutoComplete from '@component/common/SearchAutoComplete'
import SearchBar from '@component/common/SearchBar'

import useInput from '@hook/useInput'

export default function SelfMakeSearch() {
  const { state: searchText, debounceChange: onChange } = useInput<string>({
    data: '',
    setDebounce: true,
  })
  useEffect(() => {
    //react쿼리로 api 요청 보내기
  }, [searchText])
  return (
    <>
      <SearchBar placeholder="지하철 역을 입력해주세요." onChange={onChange} />
      <SearchAutoComplete />
    </>
  )
}
