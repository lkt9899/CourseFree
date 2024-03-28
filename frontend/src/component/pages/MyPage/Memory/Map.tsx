import { useState } from 'react'

import KakaoMap from '@component/kakaoMap/KakaoMap'
import FlexBox from '@component/layout/FlexBox'

// import { positions } from '@mocks/dummy';
import {
  AddCommentButton,
  AddCommentContainer,
  AddCommentInput,
  AddTagButton,
  AddTagContainer,
  AddTagInput,
  Button,
  Comment,
  CommentContainer,
  Container,
  GroupWrapper,
  Image,
  ItemWrapper,
  NoSelected,
  Section,
  SectionDate,
  SectionPlace,
  Tag,
} from '@styled/component/pages/MyPage/Memory/Map'

const Map = () => {
  const [currentSelectPlace, setCurrentSelectPlace] = useState({
    title: '',
    date: '',
    images: [],
    tags: [],
    comments: [],
    isCommentOpen: false, // 코멘트 입력창 열림 상태
    isTagOpen: false, // 태그 입력창 열림 상태
  })

  function onClickMarkerHandler(data: any) {
    setCurrentSelectPlace(data)
  }

  const toggleComment = () => {
    setCurrentSelectPlace(prevState => ({
      ...prevState,
      isCommentOpen: !prevState.isCommentOpen,
    }))
  }

  const toggleTag = () => {
    setCurrentSelectPlace(prevState => ({
      ...prevState,
      isTagOpen: !prevState.isTagOpen,
    }))
  }

  return (
    <Container>
      <FlexBox css={{ position: 'sticky', top: 0 }}>
        <KakaoMap
          width="100vw"
          height="50vh"
          onClickMarkerHandler={onClickMarkerHandler}
        />
      </FlexBox>
      {currentSelectPlace.title !== '' ? (
        <Section>
          <SectionDate>{currentSelectPlace.date}</SectionDate>
          <SectionPlace>{currentSelectPlace.title}</SectionPlace>
          <GroupWrapper>
            {currentSelectPlace.images.map((image, index) => (
              <ItemWrapper key={index}>
                <Image src={image} alt={`image-${index}`} />
              </ItemWrapper>
            ))}
            {currentSelectPlace.images.length > 0 && (
              <ItemWrapper>
                <Button>+</Button>
              </ItemWrapper>
            )}
          </GroupWrapper>
          <GroupWrapper>
            {currentSelectPlace.tags.map((tag, index) => (
              <Tag key={index}>{tag}</Tag>
            ))}
            <Tag onClick={toggleTag}>+</Tag>
          </GroupWrapper>
          <AddTagContainer show={currentSelectPlace.isTagOpen}>
            <AddTagInput
              type="text"
              placeholder="태그 추가"
              style={{
                display: currentSelectPlace.isTagOpen ? 'inline-block' : 'none',
              }}
            />
            <AddTagButton>추가</AddTagButton>
          </AddTagContainer>
          <CommentContainer>
            {currentSelectPlace.comments.map((comment, index) => (
              <Comment key={index}>{comment}</Comment>
            ))}
            <Comment onClick={toggleComment}>+</Comment>
          </CommentContainer>
          <AddCommentContainer show={currentSelectPlace.isCommentOpen}>
            <AddCommentInput type="text" placeholder="코멘트 추가" />
            <AddCommentButton>추가</AddCommentButton>
          </AddCommentContainer>
        </Section>
      ) : (
        <NoSelected>장소를 선택해 주세요</NoSelected>
      )}
    </Container>
  )
}

export default Map
