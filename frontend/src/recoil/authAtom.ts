import { atom } from 'recoil'

const authState = atom({
  key: 'authState',
  default: {
    accessToken: '',
  },
})

export { authState }
