import { css } from '@emotion/react'
import styled from '@emotion/styled'
import { ComponentProps } from 'react'

import { Colors, colors } from '@style/colorPalette'

// button 태그의 props를 상속받아 그대로 사용 가능
interface T_Button extends ComponentProps<'button'> {
  full?: boolean
  disabled?: boolean
  bgColor?: Colors
  color?: Colors
}
/**
 * @param full boolean
 * @param disabled boolean
 * @param bgColor 지정된 colorPalette
 */
const Button = styled.button<T_Button>(
  {
    cursor: 'pointer',
    fontWeight: 'bold',
    borderRadius: '6px',
    padding: '10px',
    minWidth: '50px',
  },

  // 화면 전체를 채울거면 width : 100%
  ({ full = false }) => {
    if (full)
      return css`
        width: 100%;
      `
  },
  // disabled 상태라면 투명도 높이기
  ({ disabled = false }) => {
    if (disabled)
      return css`
        opacity: 0.26;
        cursor: initial;
      `
  },
  // props로 받은 색에 따라서 색깔 변화
  ({ bgColor = 'pink300', color = 'white' }) => css`
    background-color: ${colors[bgColor]};
    color: ${colors[color]};
  `,
)
export default Button
