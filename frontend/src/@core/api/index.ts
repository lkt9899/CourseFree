import axios from 'axios'

import interceptor from './interceptor'
import API_URI from './url'

export const baseAxios = axios.create({
  baseURL: API_URI.BASE,
  headers: {
    'Content-Type': 'application/json',
    // withCredentials: true,
  },
})
export const authAxios = axios.create({
  baseURL: API_URI.BASE,
  headers: {
    'Content-Type': 'application/json',
    // withCredentials: true,
  },
})
interceptor(authAxios)
