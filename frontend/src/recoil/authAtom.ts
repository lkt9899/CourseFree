import { atom } from 'recoil'

const authState = atom({
  key: 'authState',
  default: {
    accessToken: '',
    refreshToken: '',
  },
})

export { authState }
