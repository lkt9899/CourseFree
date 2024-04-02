import QUERY_KEY from '@constant/queryKey'
import { useQuery } from '@tanstack/react-query'

import { requestStationAutoComplete } from '@api/request/station'

export const useStationAutoCompleteQuery = (keyword: string) => {
  const {
    isLoading: isStationAutoCompleteLoading,
    isError: isStationAutoCompleteError,
    data: stationList,
  } = useQuery({
    queryKey: [QUERY_KEY.STATION_AUTOCOMPLETE, keyword],
    queryFn: () => requestStationAutoComplete(keyword),
  })
  return {
    isStationAutoCompleteError,
    isStationAutoCompleteLoading,
    stationList,
  }
}
