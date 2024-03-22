import axios from 'axios'

import interceptor from './interceptor'
import API_URI from './url'

export const baseAxios = axios.create({
  baseURL: API_URI.BASE,
})
export const authAddAxios = () => {
  const instance = axios.create({
    baseURL: API_URI.BASE,
  })
  interceptor(instance)
  return instance
}

export const authAxios = authAddAxios()
