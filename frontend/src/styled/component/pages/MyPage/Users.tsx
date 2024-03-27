import styled from '@emotion/styled'
export const Container = styled.div`
  display: flex;
  justify-content: center;
  margin-top: 4rem;
  
`;

export const Item = styled.div`
  margin-right: 10px;
  margin-left: 10px;
  text-align: center;
  display: flex;
  flex-direction: column;
  &:nth-child(2) {
    margin-top: 18px;
  }
`;