import { Badge, Image } from '@chakra-ui/react'
import { Place } from '@type/course'

import TextBox from '@component/common/TextBox'
import FlexBox from '@component/layout/FlexBox'

type Props = {
  place: Place
}

/** @jsxImportSource @emotion/react */
export default function PlaceInfo({ place }: Props) {
  return (
    <FlexBox w="100%" p="10px" a="center">
      {/* 가게 이미지 */}
      <Image src={place.imageUrl} boxSize="100px" borderRadius="10px" />
      {/* 가게 설명 */}
      <FlexBox d="column" p="10px">
        <TextBox
          color="black"
          fontWeight="bold"
          typography="t6"
          onClick={() => {
            if (place.url !== ' ') window.open(place.url)
          }}
        >
          {place.name} <Badge colorScheme="pink">{place.placeCategory}</Badge>
        </TextBox>
        <TextBox typography="t7">주소 : {place.address}</TextBox>
      </FlexBox>
    </FlexBox>
  )
}
