import kiss from '@asset/kiss.jfif'
import { Image } from '@chakra-ui/react'
import { Link } from 'react-router-dom'

import TextBox from '@component/common/TextBox'
import FlexBox from '@component/layout/FlexBox'

export default function PlaceInfo() {
  return (
    <FlexBox w="100%" p="10px" a="center">
      {/* 가게 이미지 */}
      <Image src={kiss} boxSize="100px" borderRadius="10px" />
      {/* 가게 설명 */}
      <FlexBox d="column" p="10px">
        <TextBox color="pink300" fontWeight="bold" typography="t4">
          <Link to="">밸런스포케</Link>
        </TextBox>
        <TextBox>주소 : 서울 강남구 강남대로 94길 75</TextBox>
        <TextBox>영업시간 : 10:00 ~ 23:00</TextBox>
        <TextBox>브레이크 타임 : 16:00</TextBox>
      </FlexBox>
    </FlexBox>
  )
}
