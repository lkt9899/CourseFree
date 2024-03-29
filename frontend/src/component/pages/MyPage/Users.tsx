import { faQuestionCircle } from '@fortawesome/free-solid-svg-icons'
import { faHeart } from '@fortawesome/free-solid-svg-icons'
import { faUserCircle } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {
  Container,Item,InfoContainer,Title
} from '@styled/component/pages/MyPage/Users';
const name='역삼동 정현규'
const nameDefault = '상대방을 <br />추가해 주세요'

const Users = () => {
  return (
    <Container>
      <Title>
        커플 정보
        <hr />
      </Title>
      <InfoContainer>

      <Item>
        <FontAwesomeIcon icon={faUserCircle} size='5x' />
        <br />
        <p>{name}</p>
      </Item>
      <Item>
        <FontAwesomeIcon icon={faHeart} size='3x'/>
      </Item>
      <Item>
        <FontAwesomeIcon icon={faQuestionCircle} size='5x' />
        <br />
        <p dangerouslySetInnerHTML={{ __html: nameDefault }} />
      </Item>
      </InfoContainer>
    </Container>
  );
};

export default Users;
