import {
  faHeart,
  faHouse,
  faSearch,
  faUser,
} from '@fortawesome/free-solid-svg-icons'

import FooterLinkWithIcon from './FooterLinkWithIcon'

import FlexBox from '@component/layout/FlexBox'

import { Container } from '@styled/component/layout/Footer'

export default function Footer() {
  return (
    <Container>
      <FlexBox j="space-around" a="center" w="100%" h="100%">
        <FooterLinkWithIcon icon={faHouse} to="/home" title="Home" />
        <FooterLinkWithIcon icon={faSearch} to="/search" title="Search" />
        <FooterLinkWithIcon icon={faHeart} to="/myCourse" title="Course" />
        <FooterLinkWithIcon icon={faUser} to="/myPage" title="MyPage" />
      </FlexBox>
    </Container>
  )
}
