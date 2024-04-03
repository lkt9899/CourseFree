import { Badge } from '@chakra-ui/react'
import { Categories } from '@data/category'

/** @jsxImportSource @emotion/react */
interface Props {
  title: string
  type: Categories
  onClick: (type: Categories) => void
  isSelected: boolean
}
export default function CategoryItem({
  title,
  type,
  onClick,
  isSelected,
}: Props) {
  return (
    <Badge
      onClick={() => {
        onClick(type)
      }}
      fontSize="0.8rem"
      colorScheme={isSelected ? 'pink' : 'gray'}
    >
      {title}
    </Badge>
  )
}
