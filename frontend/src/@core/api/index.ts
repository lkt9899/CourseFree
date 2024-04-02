import axios from 'axios'

import API_URI from '../../constant/url'
import interceptor from './interceptor'

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
