import FlexBox from '@component/layout/FlexBox'

import {
  CourseCardContainer,
  CourseCardTitle,
  CourseCardWriter,
} from '@styled/component/pages/HomePage/CourseCard'

interface CourseCard {
  img: string
}
export default function CourseCard({ img }: CourseCard) {
  return (
    <CourseCardContainer img={img}>
      <FlexBox h="120px">
        <CourseCardTitle fontWeight="bold" typography="t4">
          청계천 알뜰살뜰 데이트 코스
        </CourseCardTitle>
        <CourseCardWriter typography="t8" color="grey">
          Course By 역삼동멀캠규
        </CourseCardWriter>
      </FlexBox>
    </CourseCardContainer>
  )
}
