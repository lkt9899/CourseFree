import styled from '@emotion/styled'
export const Container = styled.div`
  display: flex;
  justify-content: center;
  flex-direction:column;
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
export const InfoContainer = styled.div`
  background-color: lightpink;
  display: flex;
  margin: 0 auto;
  padding: 1rem;
  border-radius: 18px;
  text-align: center;
  margin-bottom: 1em;
  }
`;
export const Title = styled.div`
font-size: 1.5em;
font-weight: bold;
margin-left: 3em;
margin-right: 3em;
margin-bottom: 1em;
`;

export const Couplename = styled.div`
  font-size: 1.5em;
  margin: 0 auto;
  padding-bottom: 1em;
`