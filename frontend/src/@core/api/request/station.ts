import API_URI from '@constant/url'

import { authAxios } from '..'

export const requestStationAutoComplete = async (keyword: string) => {
  if (keyword === '') return null
  return await authAxios.get(`${API_URI.STATION_AUTOCOMPLETE}`, {
    params: {
      searchKeyword: keyword,
    },
  })
}
