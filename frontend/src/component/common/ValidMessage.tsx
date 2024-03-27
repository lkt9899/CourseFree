import { css } from '@emotion/react'

import TextBox from '@component/common/TextBox'

type Props = {
  isValid: boolean
  valid: string
  inValid: string
  show: boolean
}
const ValidMessageStyle = css``
/** @jsxImportSource @emotion/react */
export default function ValidMessage({ isValid, inValid, valid, show }: Props) {
  return (
    <>
      <TextBox
        typography="t5"
        color="primary"
        css={ValidMessageStyle}
        style={{ opacity: show ? 1 : 0 }}
      >
        {isValid ? valid : inValid}
      </TextBox>
    </>
  )
}
