import kiss from '@asset/kiss.jfif'
import { Image } from '@chakra-ui/react'
import { Place } from '@type/course'

import Button from '@component/common/Button'
import TextBox from '@component/common/TextBox'
import FlexBox from '@component/layout/FlexBox'

interface ShortPlaceInfo {
  hasButton: boolean
  buttonText: string
  onClickButton: (place: Place) => void
  place: Place
  onClickBox?: (place: Place) => void
}

export default function ShortPlaceInfo({
  hasButton,
  buttonText,
  onClickButton,
  place,
  onClickBox,
}: ShortPlaceInfo) {
  return (
    <FlexBox
      w="100%"
      p="10px"
      a="center"
      j="space-between"
      onClick={() => {
        alert('지도이동함수')
        onClickBox ? onClickBox(place) : null
      }}
    >
      {/* 컨텐츠 부분 */}
      <FlexBox>
        {/* 가게 이미지 */}

        <Image src={kiss} boxSize="60px" borderRadius="10px" />
        {/* 가게 설명 */}
        <FlexBox d="column" p="10px" h="60px" j="center">
          <TextBox color="pink300" fontWeight="bold" typography="t6">
            {place.name}
          </TextBox>
          <TextBox typography="t7">주소 : {place.address}5</TextBox>
        </FlexBox>
      </FlexBox>
      {/* 삭제 버튼 부분 */}
      {hasButton ? (
        <Button
          onClick={e => {
            e.stopPropagation()
            onClickButton(place)
          }}
        >
          {buttonText}
        </Button>
      ) : null}
    </FlexBox>
  )
}
