import styled from '@emotion/styled';

export const Header = styled.div`
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 1rem;
    background-color: #ff1493; /* 진한 분홍색 설정 */;
    font-size: 1.1em; /* 살짝 큰 폰트 사이즈 설정 */
    font-weight: bold; /* 굵은 텍스트 설정 */
    color : white;
`;

export const IconContainer = styled.div`
    display: flex;
    justify-content: space-between;
`;

export const CalendarContainer = styled.div`
    border: 1px solid #ccc;
`;

export const Container = styled.div`
    border: 1px solid #ccc;
    display: flex;
    justify-content: space-between;
    flex-direction: column;
`;

export const DayHeader = styled.div`
    display: flex;
    justify-content: space-between;
    padding: 0.5rem;
    width:92%;
    margin-left: 1.1rem;
`;

export const DayHeaderOuter = styled.div`
    display: flex;
    justify-content: space-between;
    background-color: #f0f0f0;
`;
    
export const Cell = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 0.5rem;
    cursor: pointer;
    flex: 1;

    &.disabled {
        color: #ccc;
        pointer-events: none;
    }

    &.selected {
        background-color: #007bff;
        color: #fff;
    }

    &.not-valid {
        color: #ccc;
    }

    &.light-red {
        background-color: #ffe6e6; /* 옅은 빨간 색상 */
    }

    &.light-yellow {
        background-color: #ffffcc; /* 옅은 노랑 색상 */
    }

    &.today { /* 오늘 날짜에 빨간 테두리 추가 */
        border: 3px solid red;
    }
`;

export const RegisterButton = styled.button<{ isRegistered: boolean }>`
    padding: 0.5rem 1rem;
    background-color: ${({ isRegistered }) => isRegistered ? '#dc3545' : '#007bff'};
    color: #fff;
    border: none;
    cursor: pointer;
    
`;