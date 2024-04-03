import API_URI from '@constant/url'
import { UserData,UserProfile,CoupleUserProfile } from '@type/member'

import { authAxios, baseAxios } from '@api/index'

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
      nickname: name,
    },
  })
}

export const requestUserData = (data: UserData) => {
  return authAxios.post(`${API_URI.USER_INFO}`, data)
}

export const requestGenerateLink = () => {
  return authAxios.post(`${API_URI.GENERATE_LINK}`)
}

export const requestMyFavCourse = () => {
  return authAxios.get(`${API_URI.MY_FAV_COURSE}`)
}

export const requestMyFavCourseDetail = (courseId: string) => {
  return authAxios.get(`${API_URI.MY_FAV_COURSE_DETAIL}`,{
    params: {
      courseId: courseId,
    }
  })
}

export const requestProfile = () => {
  return authAxios.get(`${API_URI.PROFILE_INFO}`)
}

export const requestModifyProfile = (data:UserProfile) => {
  return authAxios.post(`${API_URI.MODIFY_PROFILE}`,{
    CoupleInfoRequest: data
  })
}

export const requestModifyCoupleProfile = (data:CoupleUserProfile) => {
  return authAxios.post(`${API_URI.MODIFY_PROFILE_COUPLE}`,{
    MemberInfoRequest: data
  })
}