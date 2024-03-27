import axios from 'axios'

import interceptor from './interceptor'
import API_URI from './url'

export const baseAxios = axios.create({
  baseURL: API_URI.BASE,
  headers: {
    'Content-Type': 'application/json',
  },
})
export const authAxios = axios.create({
  baseURL: API_URI.BASE,
  headers: {
    'Content-Type': 'application/json',
  },
})
interceptor(authAxios)
