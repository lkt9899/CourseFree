import {
  faComment,
  faLocationDot,
  faShare,
} from '@fortawesome/free-solid-svg-icons'

import Button from '@component/common/Button'
import FlexBox from '@component/layout/FlexBox'
import FooterLinkWithIcon from '@component/layout/FooterLinkWithIcon'

import { Container } from '@styled/component/layout/Footer'

interface ArticleFooter {
  onClick: any
  id: number
}
export default function ArticleFooter({ onClick, id }: ArticleFooter) {
  return (
    <Container>
      <FlexBox w={'100%'} h="100%" a="center" j="space-around">
        <FooterLinkWithIcon
          icon={faLocationDot}
          type="course"
          id={id}
          title="지도"
        />
        <FooterLinkWithIcon
          icon={faComment}
          type="comment"
          onClick={onClick}
          id={id}
          title="댓글"
        />
        <FooterLinkWithIcon icon={faShare} type="share" id={id} title="공유" />
        <Button>코스찜하기</Button>
      </FlexBox>
    </Container>
  )
}
