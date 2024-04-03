import { faQuestionCircle } from '@fortawesome/free-solid-svg-icons'
import { faHeart } from '@fortawesome/free-solid-svg-icons'
import { faUserCircle } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { Container, Item, InfoContainer, Title, Couplename } from '@styled/component/pages/MyPage/Users';
import { useEffect, useState } from 'react'; // 추가: useState import
import { requestProfile } from '@api/request/member';
import { Link } from 'react-router-dom';

const Users = () => {
  const [memberNickname, setmemberNickname] = useState([]);
  const [partnerNickname, setpartnerNickname] = useState([]);
  const [memberimage, setmemberimage] = useState<string | null>(null);
  const [coupleNickname, setcoupleNickname] = useState([]);
  const [isCouple, setisCouple] = useState([]);
  useEffect(() => {
    requestProfile()
      .then((response) => {
        console.log(response.data)
        setmemberNickname(response.data.nickname);
        setpartnerNickname(response.data.partnerNickname);
        if (typeof response.data.image === 'string') {
          setmemberimage(response.data.image);
        }
        setcoupleNickname(response.data.coupleNickname);
        setisCouple(response.data.couple);
      })
      .catch((error) => {
        console.error('API 호출 에러:', error);
      });
  }, []);

  return (
    <Container>
      <Title>
        커플 정보
        <hr />
      </Title>
      <Couplename>
        {coupleNickname}
      </Couplename>
      <InfoContainer>
        <Item>
          {memberimage ? (
            <img src={memberimage} alt="Member Image" />
          ) : (
            <FontAwesomeIcon icon={faUserCircle} size='5x' />
          )}
          <br />
          <p>{memberNickname}</p>
        </Item>
        <Item>
          <FontAwesomeIcon icon={faHeart} size='3x' />
        </Item>
        <Item>
          <Link to={isCouple ? '#' : '/generateLink'}> {/* isCouple이 true일 때는 #으로 설정 */}
            <FontAwesomeIcon icon={faQuestionCircle} size='5x' />
            <br />
            {partnerNickname ? (
              <p dangerouslySetInnerHTML={{ __html: partnerNickname }} />
            ) : (
              <p>상대방을 <br />추가해 주세요</p>
            )}
          </Link>
        </Item>

      </InfoContainer>
    </Container>
  );
};

export default Users;
