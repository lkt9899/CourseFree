import TitleBar from '@component/common/TitleBar'
import FlexBox from '@component/layout/FlexBox'

import { Container } from '@styled/page/ArticleTitle'

type Props = {
  title: string
}
export default (function ArticleTitle({ title }: Props) {
  return (
    <Container>
      <FlexBox w={'100%'} t="center" h="auto" display="block">
        <TitleBar hasBackPage title={title} />
        {/* <TextBox typography="t8" textAlign="right" color="pink300">
          맛꿀마 남산 데이트
        </TextBox> */}
        {/* <TextBox typography="t8" textAlign="right" color="gray">
          Course By 화양동민차장
        </TextBox> */}
      </FlexBox>
    </Container>
  )
})
