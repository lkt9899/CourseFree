import { Avatar } from '@chakra-ui/react'

import TextBox from '@component/common/TextBox'
import FlexBox from '@component/layout/FlexBox'
import Spacing from '@component/layout/Spacing'

export default function PlaceAvatar({
  isSelected,
  src,
  placeName,
  onClick,
  idx,
}: any) {
  return (
    <>
      <FlexBox
        d="column"
        a="center"
        j="center"
        p={'10px'}
        onClick={() => {
          onClick(idx)
        }}
      >
        <Avatar name="Dan Abrahmov" src={src} borderColor={'green'} />
        <Spacing size="5px" />
        <TextBox
          typography="t8"
          color={isSelected ? 'primary' : 'gray'}
          fontWeight={isSelected && 'bold'}
        >
          {placeName.length >= 5 ? placeName.slice(0, 6) + '...' : placeName}
        </TextBox>
      </FlexBox>
      {/* {!isLast && <Stroke />} */}
    </>
  )
}
