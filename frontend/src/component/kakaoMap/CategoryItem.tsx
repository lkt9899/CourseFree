import { Categories } from '@data/category'
import { css } from '@emotion/react'

import Button from '@component/common/Button'
import TextBox from '@component/common/TextBox'
import FlexBox from '@component/layout/FlexBox'

const CategoryItemContainer = css`
  background-color: white;
  border-radius: 100px;
  padding: 5px;
  border: 1px solid var(--primary);
  overflow: hidden;
`
/** @jsxImportSource @emotion/react */
interface Props {
  title: string
  type: Categories
  onClick: (type: Categories) => void
}
export default function CategoryItem({ title, type, onClick }: Props) {
  return (
    <FlexBox a="center" j="center" css={CategoryItemContainer}>
      <Button
        onClick={() => {
          onClick(type)
        }}
      >
        <TextBox fontWeight="bold" typography="t6" color="white">
          {title}
        </TextBox>
      </Button>
    </FlexBox>
  )
}
