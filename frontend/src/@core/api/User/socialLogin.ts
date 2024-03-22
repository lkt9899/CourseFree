import { baseAxios } from '@api/index'
import URL from '@api/url'

/**
 *
 * @param code : 카카오에서 발급해주는 인가코드를 담아서 보냅니다.
 * @param type : 네이버, 카카오 등의 소셜 타입
 * @returns Promise 객체
 */
export const requestAuthorizationCode = async (code: string, type: string) => {
  return await baseAxios.post(`${URL.KAKAO_LOGIN}/${type}`, {
    AuthorizationCode: code,
  })
}
