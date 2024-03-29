import { useParams } from 'react-router-dom';
import TitleBar from '@component/common/TitleBar'

const FavoriteDetail = () => {
  const { courseId } = useParams(); // 동적으로 변경된 URL의 매개변수를 가져옴

  return (
    <div>
      <TitleBar title="내가 찜한 목록" hasBackPage />
      <h2>Favorite Detail Page</h2>
      <p>Course ID: {courseId}</p>
    </div>
  );
};

export default FavoriteDetail;