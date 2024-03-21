import { useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import { useRecoilState } from 'recoil'

import { requestAuthorizationCode } from '@api/signUp'

import { authState } from '@recoil/authAtom'

export default function useSocialLogin() {
  // recoil에 전역으로 저장할 로그인 상태
  const [userInfo, setUserInfo] = useRecoilState(authState)
  const navigate = useNavigate()
  // URL의 쿼리파라미터 값 받아오기
  const PARAMS = new URL(document.location.toString()).searchParams

  // 인가코드 얻기
  const KAKAO_CODE = PARAMS.get('code')

  // Access Token 받아오기
  function getAccessToken() {
    try {
      const response = requestAuthorizationCode(KAKAO_CODE, 'kakao')
      // 받아온 액세스 토큰와 리프레시 토큰을 스토리지에 담기
      const accessToken = response.data.accessToken
      const refreshToken = response.data.refreshToken
      sessionStorage.setItem('jwt_token', accessToken)
      sessionStorage.setItem('refresh_token', refreshToken)

      // 여기까지 오류 없이 왔다면 상태 업데이트
      setUserInfo(null)
      // 그리고 페이지 전환
      navigate('/home')
    } catch (error) {
      alert(e)
    }
  }

  useEffect(() => {
    if (KAKAO_CODE && !userInfo.accessToken) {
      getAccessToken()
    }
  }, [KAKAO_CODE, userInfo])
}
