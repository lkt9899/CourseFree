import API_URI from '@constant/url'
import { ArticleDetail, TComment } from '@type/article'

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
export const requestArticleDetail = async (id: number) => {
  return await authAxios.get<ArticleDetail>(`${API_URI.ARTICLE_DETAIL}`, {
    params: {
      postId: id,
    },
  })
}
export const requestArticleComment = async (id: number) => {
  return await authAxios.get<TComment[]>(`${API_URI.ARTICLE_COMMENT}`, {
    params: {
      postId: id,
    },
  })
}
export const requestSubmitComment = async (postId, content) => {
  console.log(postId, content, '123123')
  return await authAxios.post(`${API_URI.SUBMIT_COMMENT}`, {
    postId,
    content,
  })
}
