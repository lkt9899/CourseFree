import { UserData } from '@type/member'

import { authAxios, baseAxios } from '@api/index'
import API_URI from '@api/url'

/**
 *
 * @param code : 카카오에서 발급해주는 인가코드를 담아서 보냅니다.
 * @param type : 네이버, 카카오 등의 소셜 타입
 * @returns Promise 객체
 */
export const requestAuthorizationCode = (code: string, type: string) => {
  if (type === 'kakao')
    return baseAxios.post(`${API_URI.KAKAO_LOGIN}/${type}`, code)
}

export const requestNickNameValidCheck = (name: string) => {
  return authAxios.get(`${API_URI.VALID_CHECK}`, {
    params: {
      nickName: name,
    },
  })
}

export const requestUserData = (data: UserData) => {
  return authAxios.post(`${API_URI.USER_INFO}`, {
    ProfileInfoRequest: data,
  })
}
