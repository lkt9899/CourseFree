import styled from '@emotion/styled';
// 컨테이너
export const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 3rem;
`;

export const Section = styled.div`
  margin-bottom: 0.5rem;
  margin-left: 1.5rem;
`;
// 섹션 헤더
export const SectionHeader = styled.h2`
  margin-bottom: 0.5rem;
  font-size: 1.2rem;
  font-weight: bold;
`;
export const SectionDate = styled.div`
  margin-bottom: 0.5rem;
  font-weight: bold;
`;
export const SectionPlace = styled.div`
  margin-bottom: 0.5rem;
  font-size: 1.5rem;
  font-weight: bold;
  color: #ff1493; /* 진한 분홍색 설정 */
`;

// 이미지 및 태그 그룹 래퍼
export const GroupWrapper = styled.div`
  display: flex;
  flex-wrap: wrap;
  margin-bottom: 1rem;
`;

export const ItemWrapper = styled.div`
  margin-right: 3px;
  margin-bottom: 3px;
  padding: 10px;
  background-color: #f0f0f0; /* 옅은 회색 배경 */
  border-radius: 2px; /* 모서리를 둥글게 */
`;

// 이미지 스타일
export const Image = styled.img`
  width: 100px;
  height: 100px;
`;

// 버튼 스타일
export const Button = styled.button`
  width: 100px;
  height: 100px;
  margin-top: 5px;
`;

// 태그 스타일
export const Tag = styled.div`
  background-color: #FFF5F7;
  border-radius: 8px;
  padding: 5px 10px;
  margin-right: 5px;
  margin-bottom: 5px;
  border: 2px solid #ff8c94;
`;

// 코멘트 컨테이너
export const CommentContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 1rem;
  padding: 1rem;
  background-color: #FFF5F7;
  border: 2px solid #ff8c94;
  border-radius: 8px;
  width: 95%;
`;

// 코멘트 스타일
export const Comment = styled.div`
  margin-top: 0.5rem;
  padding: 0.5rem 1rem;
  background-color: #fff;
  border-radius: 8px;
  border: 2px solid #ff8c94;
  width: 100%;
`;

export const AddCommentInput = styled.input`
  flex: 1;
  padding: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  margin-right: 1rem;
`;

export const AddCommentButton = styled.button`
  padding: 0.5rem 1rem;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
`;
interface AddContainerProps {
  show: boolean;
}

export const AddCommentContainer = styled.div<AddContainerProps>`
  display: ${props => (props.show ? 'flex' : 'none')};
  align-items: center;
  margin-top: 1rem;
`;

export const AddTagContainer = styled.div<AddContainerProps>`
  margin-top: 1rem;
  display: ${props => (props.show ? 'block' : 'none')};
`;


export const AddTagInput = styled.input`
  padding: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  margin-right: 1rem;
`;

export const AddTagButton = styled.button`
  padding: 0.5rem 1rem;
  background-color: #28a745;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
`;

export const NoSelected = styled.p`
    align-items: center;
    text-align:center;
    font-size: 2em; /* 살짝 큰 폰트 사이즈 설정 */
    font-weight: bold; /* 굵은 텍스트 설정 */
    color: #ff1493; /* 진한 분홍색 설정 */
    margin-top: 50px;
`;