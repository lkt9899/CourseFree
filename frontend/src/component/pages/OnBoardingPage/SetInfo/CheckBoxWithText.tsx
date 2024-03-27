import { Checkbox } from '@chakra-ui/react'
import { memo } from 'react'

import TextBox from '@component/common/TextBox'

import { Colors } from '@style/colorPalette'

type CheckBoxWithText = {
  children: string
  size?: string
  color?: Colors
  isChecked: boolean
  onChange: () => void
}

export default memo(function CheckBoxWithText({
  children,
  size = 'lg',
  color = 'primary',
  onChange,
  isChecked,
}: CheckBoxWithText) {
  return (
    <Checkbox
      isChecked={isChecked}
      size={size}
      colorScheme="pink"
      onChange={onChange}
    >
      <TextBox typography="t4" color={color}>
        {children}
      </TextBox>
    </Checkbox>
  )
})
