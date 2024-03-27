import { faGear } from '@fortawesome/free-solid-svg-icons';
import { faHeart } from '@fortawesome/free-solid-svg-icons';
import { faCalendarAlt } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { NavLink } from 'react-router-dom';
import {
  Container,
  Item
} from '@styled/component/pages/MyPage/Menu';

const Menu = () => {
  return (
    <Container>
      <Item>
        <NavLink to='/mypage/log'>
          <FontAwesomeIcon icon={faCalendarAlt} size='5x' />
          <br />
          <p>우리의 일정</p>
        </NavLink>
      </Item>
      <Item>
        <NavLink to='/mypage/memory'>
          <FontAwesomeIcon icon={faHeart} size='5x' />
          <br />
          <p>우리의 기록</p>
        </NavLink>
      </Item>
      <Item>
        <NavLink to='/mypage/setting'>
          <FontAwesomeIcon icon={faGear} size='5x' />
          <br />
          <p>설정</p>
        </NavLink>
      </Item>
    </Container>
  );
};

export default Menu;
