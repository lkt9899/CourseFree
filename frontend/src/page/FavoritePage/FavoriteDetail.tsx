import { useParams } from 'react-router-dom';
import TitleBar from '@component/common/TitleBar';
import Footer from '@component/layout/Footer';
import Section from '@component/layout/Section';
import styled from 'styled-components';

import FullPageLoading from '@component/common/FullPageLoading'
import { useCourseDetailQuery } from '@hook/ReactQuery/useCourseDetailQuery'
import LongCourseInfo from '@component/Course/LongCourseInfo'


export const TitleContainer = styled.div`
  display: flex; /* 내부 요소들을 가로로 정렬 */
  justify-content: center; /* 가로 방향으로 중앙 정렬 */
  align-items: center; /* 세로 방향으로 중앙 정렬 */
`;

const FavoriteDetail = () => {
  const { courseId } = useParams();
  const parsedCourseId = parseInt(courseId);
  const { data, isLoading } = useCourseDetailQuery(parsedCourseId)
  

  if (isLoading) return <FullPageLoading />


  return (
    <>
      <Section>
        <TitleBar title="내가 찜한 목록" hasBackPage />
          <LongCourseInfo courseData={data.data} />
      </Section>
      <Footer />
    </>
  );
};

export default FavoriteDetail;
