import { faPhone, faUserCircle } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { Container, Title, InfoContainer, Name, NickName, Age, Icon, Couple, Phone, MainContainer, BottomContainer } from '@styled/component/pages/MyPage/Setting/Setting';



const user = { 'name': '박희준', 'nickname': '역삼동 정현규', 'age': 28, 'gender': '남', 'couple': '@@@', 'phone': '010-1234-5678' }

const setting = () => {
  return (
    <Container>
      <Title>
        내 정보
        <hr />
      </Title>
      <InfoContainer>
        <Icon>
          <FontAwesomeIcon icon={faUserCircle} size='5x' />
        </Icon>
        <Name>
          <p>{user.name}</p>
        </Name>
        <NickName>
          <p>{user.nickname}</p>
        </NickName>
        <Phone>
          <FontAwesomeIcon icon={faPhone} />  {user.phone}
        </Phone>
        <Age>
          <p>{user.age} {user.gender}</p>
        </Age>
        <Couple>
          <p>{user.couple}님과 커플이예요</p>
        </Couple>
      </InfoContainer>
      <MainContainer>
        <p></p>
        <p></p>
        <p>공지 사항</p>
        <p>서비스 정보 및 약관</p>
        <p></p>
        <p></p>
      </MainContainer>
      <BottomContainer>
        <p>회원 탈퇴</p>
        <p>커플 해제</p>
      </BottomContainer>
    </Container>
  );
};

export default setting;
