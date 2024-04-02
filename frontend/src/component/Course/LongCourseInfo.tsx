import PlaceInfo from './PlaceInfo'

import TextBox from '@component/common/TextBox'
import KakaoMap from '@component/kakaoMap/KakaoMap'
import FlexBox from '@component/layout/FlexBox'

export default function LongCourseInfo() {
  return (
    <FlexBox d="column" a="center">
      <TextBox fontWeight="bold" padding="10px" typography="t5">
        역삼역 내향인 커플 데이트
      </TextBox>
      <KakaoMap width="100%" height="300px" />
      <PlaceInfo />
      <PlaceInfo />
      <PlaceInfo />
      <PlaceInfo />
    </FlexBox>
  )
}
