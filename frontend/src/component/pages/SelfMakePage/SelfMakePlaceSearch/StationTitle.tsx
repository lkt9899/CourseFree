import { memo } from 'react'

import TextBox from '@component/common/TextBox'

type Props = { name: string }

export default memo(function StationTitle({ name }: Props) {
  return (
    <TextBox fontWeight="bold" typography="t4">
      {name}
    </TextBox>
  )
})
