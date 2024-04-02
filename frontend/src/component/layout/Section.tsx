import styled from '@emotion/styled'

const Section = styled.section`
  width: 100%;
  height: 100%;
  position: relative;
  padding-bottom: 4rem;
  //
  -ms-overflow-style: none;
  // 스크롤바 UI는 없애고, 스크롤 기능은 유지
  ::-webkit-scrollbar {
    display: none;
  }
  overflow-y: scroll;
  overscroll-behavior-y: contain;

  // 스크롤 기능을 유
  .swiper-pagination-bullet-active {
    background-color: var(--primary) !important;
  }
`

export default Section
