/* eslint-disable react-hooks/rules-of-hooks */
import { faPhone, faUserCircle } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { Container, Title, InfoContainer, Name, NickName, Age, Icon, Couple, Phone, MainContainer, BottomContainer } from '@styled/component/pages/MyPage/Setting/Setting';
import { useEffect, useState } from 'react'; // 추가: useState import
import { requestProfile } from '@api/request/member';





const setting = () => {
  const [memberNickname, setmemberNickname] = useState([]);
  const [partnerNickname, setpartnerNickname] = useState([]);
  const [memberimage, setmemberimage] = useState<string | null>(null);
  // const [coupleNickname, setcoupleNickname] = useState([]);
  const [gender, setgender] = useState([]);
  useEffect(() => {
    requestProfile()
      .then((response) => {
        console.log(response.data)
        setmemberNickname(response.data.nickname);
        setpartnerNickname(response.data.partnerNickname);
        if (typeof response.data.image === 'string') {
          setmemberimage(response.data.image);
        }
        // setcoupleNickname(response.data.coupleNickname);
        setgender(response.data.gender);
      })
      .catch((error) => {
        console.error('API 호출 에러:', error);
      });
  }, []);
  return (
    <Container>
      <Title>
        내 정보
        <hr />
      </Title>
      <InfoContainer>
        <Icon>
          {memberimage ? (
            <img src={memberimage} alt="Member Image" />
          ) : (
            <FontAwesomeIcon icon={faUserCircle} size='5x' />
          )}
        </Icon>
        <Name>
          {/* <p>{user.name}</p> */}
          <p>{memberNickname}</p>
        </Name>
        <NickName>
        </NickName>
        {/* <Phone>
          <FontAwesomeIcon icon={faPhone} />  {user.phone}
        </Phone> */}
        <Age>
          <p>{gender}</p>
        </Age>
        <Couple>
          {partnerNickname && (
            <p>{partnerNickname}님과 커플이예요</p>
          )}
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
