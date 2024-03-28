import styled from '@emotion/styled';

export const Container = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    border: 1px solid #ccc; /* 테두리 추가 */
`;

export const Photo = styled.img`
    margin-right: 20px;
    margin-left: 7px;
    margin-top: 7px;
    margin-bottom:7px;
    height:100px;
    width:130px;
    background-color: lightgreen;
`;

export const Results = styled.div`
    display:flex;
    border: 1px solid #ccc; /* 테두리 추가 */
    cursor: pointer; /* 커서를 포인터로 변경하여 클릭 가능하도록 설정 */
`;

export const ResultTitle = styled.p`
    margin-bottom: 2px;
    font-size: 1.1em; /* 살짝 큰 폰트 사이즈 설정 */
    font-weight: bold; /* 굵은 텍스트 설정 */
    color: #ff1493; /* 진한 분홍색 설정 */
    margin-top:12px;
`;
export const ResultNav = styled.div`
    margin: 4px;
    font-size: 1.1em; /* 살짝 큰 폰트 사이즈 설정 */
    font-weight: bold; /* 굵은 텍스트 설정 */
    display:flex;
    justify-content: space-between
`;
export const ResultNavLeft = styled.div`
    margin-bottom: 2px;
    display:flex;
`;
export const ResultNavRight = styled.div`
    margin-bottom: 2px;
    display:flex;
`;

export const ResultBody = styled.div`
    margin-bottom: 2px;
    width : 180px;
    display: flex;
    flex-direction: column;
`;
export const ResultTable = styled.table`
    margin-bottom: 2px;
    border-collapse: collapse;
    width: 100%;
    margin-top:10px;
`;

export const ResultTd = styled.td`
padding-left:10px;
// font-size:0.7em
`;

export const ResultTh = styled.th`
padding : 4px;
font-weight: normal;
// font-size:0.7em
width:30%
`;

export const LinkTo = styled.div`
    font-weight:bold;
    background-color:lightpink;
    width:45%;
    text-align:center;
    border-radius: 8px;
    margin-left:170px;
`;