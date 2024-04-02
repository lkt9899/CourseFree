import { Circle } from '@chakra-ui/react'

import FlexBox from '@component/layout/FlexBox'

interface PlaceInfoWithOrder {
  children: React.ReactNode
  order: number
}

export default function PlaceInfoWithOrder({
  order,
  children,
}: PlaceInfoWithOrder) {
  return (
    <FlexBox a="center" j="space-between" w="100%" p={'10px'}>
      <Circle size={'25px'} backgroundColor={'hotpink'} color="white">
        {order}
      </Circle>
      {children}
    </FlexBox>
  )
}
