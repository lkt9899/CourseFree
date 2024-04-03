import { Link } from 'react-router-dom';
import { useEffect, useState } from 'react'; // 추가: useState import
import { requestMyFavCourse } from '@api/request/member';
import { NameContainer, Container, NameWrapper, Image, ContentWrapper, Title, Button } from '@styled/component/pages/FavoritePage/FavoriteList';

const FavoriteList = () => {
  const [courses, setCourses] = useState([]); // 추가: courses 상태 추가

  useEffect(() => {
    // API 호출하여 코스 목록 가져오기
    requestMyFavCourse()
      .then((response) => {
        console.log(response.data.myFavoriteCourseList)
        // 코스 목록 설정
        setCourses(response.data.myFavoriteCourseList);
      })
      .catch((error) => {
        console.error('API 호출 에러:', error);
      });
  }, []);

  return (
    <>
      <NameContainer>
        <NameWrapper><strong>역삼동 정현규</strong> 님의 찜 목록</NameWrapper>
      </NameContainer>
      {courses.map((course, index) => (
        <Container key={index}>
          <Image src={course.imageUrl} alt={course.title} />
          <ContentWrapper>
            <Title>{course.title}</Title>
            {/* 링크에 course.title을 prop으로 전달하여 보내기 */}
            <Link to={`/favorite/${course.courseId}?title=${encodeURIComponent(course.title)}`}>
              <Button>바로가기</Button>
            </Link>

          </ContentWrapper>
        </Container>
      ))}
    </>
  );
};

export default FavoriteList;
