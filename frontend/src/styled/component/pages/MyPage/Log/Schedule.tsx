import styled from '@emotion/styled';

export const Container = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    border: 1px solid #ccc; /* 테두리 추가 */
`;

export const DataSet = styled.div`
    display: flex;
    height:100px;
`;

export const Photo = styled.img`
    margin-right: 20px;
    height:100%;
    width:30%;
`;

export const Content = styled.div`
    display: flex;
    flex-direction: column;
    width:70%;
`;

export const ContentUp = styled.div`
    display: flex;
    flex-direction: column;
    font-size: 1.1em; /* 살짝 큰 폰트 사이즈 설정 */
    font-weight: bold; /* 굵은 텍스트 설정 */
    color: #ff1493; /* 진한 분홍색 설정 */
`;

export const ContentDown = styled.div`
    display: flex;
    flex-direction: column;
    font-weight: bold; /* 굵은 텍스트 설정 */
`;

export const DateText = styled.p`
    font-weight: bold;
`;

export const Place = styled.p`
    margin-bottom: 2px;
`;

export const Button = styled.button`
    margin: 7px; /* 버튼과 일정 정보 사이의 간격 조절 */
    font-weight: bold; /* 굵은 텍스트 설정 */
`;

export const ExpandedContent = styled.div`
    
border: 1px solid #ccc; /* 테두리 추가 */
`

export const ExpandedPlace = styled.p`
    display:flex;
    border: 1px solid #ccc; /* 테두리 추가 */
    cursor: pointer; /* 커서를 포인터로 변경하여 클릭 가능하도록 설정 */
`;

export const ExpandedName = styled.p`
    margin-bottom: 2px;
    font-size: 1.1em; /* 살짝 큰 폰트 사이즈 설정 */
    font-weight: bold; /* 굵은 텍스트 설정 */
    color: #ff1493; /* 진한 분홍색 설정 */
`;

export const ExpandedBody = styled.p`
    margin-bottom: 2px;
    font-weight: bold; /* 굵은 텍스트 설정 */
    display: flex;
    flex-direction: column;
`;
export const Expanded = styled.p`
    margin-bottom: 2px;
    font-weight: bold; /* 굵은 텍스트 설정 */
    display: flex;
    flex-direction: column;
`;
export const ExpandedTable = styled.table`
    border-collapse: collapse;
    width: 100%;
`;

export const ExpandedTd = styled.td`
padding-left:10px;
`;

export const ExpandedTh = styled.th`
padding : 4px;
`;

export const NoSchedule = styled.p`
    align-items: center;
    text-align:center;
    font-size: 2em; /* 살짝 큰 폰트 사이즈 설정 */
    font-weight: bold; /* 굵은 텍스트 설정 */
    color: #ff1493; /* 진한 분홍색 설정 */
    margin-top: 50px;
`;