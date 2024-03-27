import { faPhone } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { Container, Title, InfoContainer, Name, NickName, Age, Couple, Phone, MainContainer, BottomContainer } from '@styled/component/pages/MyPage/Setting/Setting';


const user = { 'name': '박희준', 'nickname': '역삼동 정현규', 'age': 28, 'gender': '남', 'couple': '@@@', 'phone': '010-1234-5678' }

const setting = () => {
  return (
    <Container>
      <Title>
        내 정보
        <hr />
      </Title>
      <InfoContainer>
        <Name>
          <p>{user.name}</p>
        </Name>
        <Age>
          <p>{user.age} {user.gender}</p>
        </Age>
        <Couple>
          <p>{user.couple}님과 커플이예요</p>
        </Couple>
      </InfoContainer>
      <NickName>
        <p>닉네임 : {user.nickname}</p>
      </NickName>
      <Phone>
        전화번호 : <FontAwesomeIcon icon={faPhone} /> {user.phone}
      </Phone>
      <MainContainer>

      </MainContainer>
      <BottomContainer>
        <p>회원 탈퇴</p>
        <p>커플 해제</p>
      </BottomContainer>
    </Container>
  );
};

export default setting;
