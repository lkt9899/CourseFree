import { useQuery } from '@tanstack/react-query'
import { MapLocation } from '@type/kakaoMap'
import QUERY_KEY from 'src/constant/queryKey'

import { requestPlaceInfo } from '@api/request/course'

export const usePlaceInfoQuery = (mapInfo: MapLocation) => {
  const {
    isLoading: isPlaceInfoListLoading,
    isError: isPlaceInfoListError,
    data: placeInfoList,
  } = useQuery({
    queryKey: [QUERY_KEY.PLACE_INFO, mapInfo],
    queryFn: () => requestPlaceInfo(mapInfo),
  })
  return { isPlaceInfoListLoading, isPlaceInfoListError, placeInfoList }
}
