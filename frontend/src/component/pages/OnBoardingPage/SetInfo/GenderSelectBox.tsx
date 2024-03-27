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
          changeGender('man')
        }}
        backgroundColor={gender === 'man' ? 'pink' : 'lightGray'}
      />
      <Avatar
        name="여"
        boxSize="100px"
        color="black"
        onClick={() => {
          changeGender('woman')
        }}
        backgroundColor={gender === 'woman' ? 'pink' : 'lightGray'}
      />
    </FlexBox>
  )
}
