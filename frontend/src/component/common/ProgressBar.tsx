import { CSSProperties } from 'react'

import { Colors } from '@style/colorPalette'

type ProgressBar = {
  color?: Colors
  value: number
  max: number
  width: CSSProperties['width']
  height: CSSProperties['height']
}
/**
 * @summary : 진행도를 나타내는 컴포넌트
 * @param color : 바 색깔
 * @param progress : 실제 진행도
 * @param total : 전체 기준
 */

/** @jsxImportSource @emotion/react */
export default function ProgressBar({
  value,
  max,
  width,
  height,
}: ProgressBar) {
  return (
    <progress
      max={max}
      value={value}
      css={{
        height,
        width,
      }}
    ></progress>
  )
}
