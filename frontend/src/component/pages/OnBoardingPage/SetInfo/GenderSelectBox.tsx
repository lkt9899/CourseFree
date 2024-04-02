import { Avatar } from '@chakra-ui/react'

import FlexBox from '@component/layout/FlexBox'

type Props = {
  gender: string
  changeGender: (gender) => void
}

export default function GenderSelectBox({ gender, changeGender }: Props) {
  return (
    <FlexBox p="10px" j="space-evenly">
      <Avatar
        name="남"
        boxSize="100px"
        color="black"
        onClick={() => {
          changeGender('MALE')
        }}
        backgroundColor={gender === 'MALE' ? 'pink' : 'lightGray'}
      />
      <Avatar
        name="여"
        boxSize="100px"
        color="black"
        onClick={() => {
          changeGender('FEMALE')
        }}
        backgroundColor={gender === 'FEMALE' ? 'pink' : 'lightGray'}
      />
    </FlexBox>
  )
}
