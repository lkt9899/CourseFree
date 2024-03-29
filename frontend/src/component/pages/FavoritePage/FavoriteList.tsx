import { Link } from 'react-router-dom';
import styled from '@emotion/styled';

const Container = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 10px;
  border: 2px solid #ff69b4; /* 밝은 분홍색 테두리 */
  border-radius: 10px; /* 둥근 테두리 */
  max-width: 500px;
  margin: 0 auto 10px auto; /* 수평 가운데 정렬 및 아래 여백 추가 */
  background-color: rgba(255, 192, 203, 0.2); /* 밝은 분홍색 배경 (알파 값 추가) */
`;

const Image = styled.img`
  width: 100px;
  height: 100px;
  margin-right: 20px;
  border-radius: 5px;
`;

const ContentWrapper = styled.div`
  flex: 1;
`;

const Title = styled.h2`
  margin: 0;
  font-size: 18px;
  min-height: 60px;
`;

const Button = styled.button`
  padding: 8px 12px;
  background-color: #ff69b4;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  margin-left:220px;
`;
const NameContainer = styled.div`
  display: flex; /* 내부 요소들을 가로로 정렬 */
  justify-content: center; /* 가로 방향으로 중앙 정렬 */
  align-items: center; /* 세로 방향으로 중앙 정렬 */
`;
const NameWrapper = styled.div`
  text-align: center;
  margin-bottom: 10px;
  font-size: 18px;
  padding: 10px; /* 콘텐츠와 테두리 간의 간격 조정 */
  border: 1px solid #ff69b4; /* 테두리 추가 */
  border-radius: 10px; /* 둥근 테두리 */
  display: inline-block; /* 인라인 블록 요소로 설정 */
  width: auto; /* 텍스트의 너비에 맞게 설정 */
`;

const name = '역삼동 정현규';

const courses = [
  { title: '1번 코스 글자 수가 몇글자까지 가능할까요 여기서 더하면?', image_url: '사진 URL', course_id: 1 },
  { title: '2번 코스', image_url: '사진 URL', course_id: 2 },
  { title: '3번 코스', image_url: '사진 URL', course_id: 3 },
];

const FavoriteList = () => {

  return (
    <>
      <NameContainer>
      <NameWrapper><strong>{name}</strong> 님의 찜 목록</NameWrapper>
      </NameContainer>
      {courses.map((course, index) => (
        <Container key={index}>
          <Image src={course.image_url} alt={course.title} />
          <ContentWrapper>
            <Title>{course.title}</Title>
            <Link to={`/favorite/${course.course_id}`}>
            <Button>바로가기</Button>
            </Link>
          </ContentWrapper>
        </Container>
      ))}
    </>
  );
};

export default FavoriteList;
