import API_URI from '@constant/url'

import { authAxios } from '..'

import { ArticleFilterType } from '@component/pages/HomePage/ArticleViews'

export const requestHotArticle = async () => {
  return await authAxios.get(`${API_URI.HOT_ARTICLE}`)
}

export const requestArticles = async (pageParam, filter: ArticleFilterType) => {
  return await authAxios.get(`${API_URI.SEARCH_ARTICLE}/${filter}`, {
    params: {
      offset: pageParam,
    },
  })
}
