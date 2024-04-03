import { useParams } from 'react-router-dom';
import TitleBar from '@component/common/TitleBar';
import { useState, useEffect } from 'react';
import { requestMyFavCourseDetail } from '@api/request/member';
import Footer from '@component/layout/Footer';
import Section from '@component/layout/Section';
import styled from 'styled-components';
import { useLocation } from 'react-router-dom';

// 스타일드 컴포넌트를 사용하여 스타일을 정의합니다.
const PlaceContainer = styled.div`
  margin-bottom: 20px;
  padding: 15px;
  background-color: ${({ url }) => (url.trim() ? '#f8f9fa' : 'transparent')};
  border-radius: 10px;
  cursor: ${({ url }) => (url.trim() ? 'pointer' : 'default')};
  transition: background-color 0.3s ease;
  &:hover {
    background-color: ${({ url }) => (url.trim() ? '#e9ecef' : 'transparent')};
  }
  // URL에 띄어쓰기가 없는 경우에만 포인터 이벤트 활성화
  pointer-events: ${({ url }) => (url.trim() ? 'auto' : 'none')};
`;

const Title = styled.div`
  font-weight: bold;
  font-size : 23px;
  margin-left: 1em;
  margin-bottom: 1em;
  padding: 10px; /* 콘텐츠와 테두리 간의 간격 조정 */
  border: 1px solid #ff69b4; /* 테두리 추가 */
  border-radius: 10px; /* 둥근 테두리 */
  display: inline-block; /* 인라인 블록 요소로 설정 */
  width: auto; /* 텍스트의 너비에 맞게 설정 */
`;

const PlaceCategory = styled.p`
  font-weight: bold;
  color: #ff1493;
`;

const PlaceName = styled.p`
  font-size: 18px;
  margin-top: 5px;
`;

const PlaceAddress = styled.p`
  color: #666;
  margin-top: 5px;
`;

export const TitleContainer = styled.div`
  display: flex; /* 내부 요소들을 가로로 정렬 */
  justify-content: center; /* 가로 방향으로 중앙 정렬 */
  align-items: center; /* 세로 방향으로 중앙 정렬 */
`;

const FavoriteDetail = () => {
  const { courseId } = useParams();
  const [course, setCourse] = useState([]);
  const location = useLocation();
  const searchParams = new URLSearchParams(location.search);
  const title = searchParams.get('title');

  useEffect(() => {
    requestMyFavCourseDetail(courseId)
      .then((response) => {
        setCourse(response.data.placeDtoList);
      })
      .catch((error) => {
        console.error('API 호출 에러:', error);
      });
  }, []);

  const handleContainerClick = (url) => {
    if (url.trim()) {
      window.open(url, '_blank');
    }
  };

  return (
    <>
      <Section>
        <TitleBar title="내가 찜한 목록" hasBackPage />
        <TitleContainer>

          <Title>
            {title}
          </Title>
        </TitleContainer>
        {course.map((place, index) => (
          <PlaceContainer
            key={index}
            onClick={() => handleContainerClick(place.url)}
            url={place.url}
          >
            <PlaceCategory>{place.placeCategory}</PlaceCategory>
            <PlaceName>{place.name}</PlaceName>
            <PlaceAddress>{place.address}</PlaceAddress>
          </PlaceContainer>
        ))}
      </Section>
      <Footer />
    </>
  );
};

export default FavoriteDetail;
