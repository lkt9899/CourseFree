import { AxiosInstance } from 'axios'

import { getJWT } from '@util/jwt'

export default function interceptor(instance: AxiosInstance) {
  // 요청 전 JWT 실어 보내는 로직
  addAccessTokenOnRequest(instance)
}

function addAccessTokenOnRequest(instance: AxiosInstance) {
  instance.interceptors.request.use(
    config => {
      const token = getJWT('jwt_token')
      if (token) {
        config.headers.Authorization = `Bearer ${token}`
      }

      return config
    },
    error => Promise.reject(error.response),
  )
}
