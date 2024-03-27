import useSocialLogin from '@hook/useSocialLogin'

export default function KakaoLoginPage() {
  useSocialLogin('kakao')

  return <div>로그인중입니다.</div>
}
