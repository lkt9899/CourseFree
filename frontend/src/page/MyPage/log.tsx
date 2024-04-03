import { Outlet } from 'react-router-dom'

import TitleBar from '@component/common/TitleBar'
import Footer from '@component/layout/Footer'
import Section from '@component/layout/Section'
import Spacing from '@component/layout/Spacing'
import Calender from '@component/pages/MyPage/Log/Calendar'
import Schedule from '@component/pages/MyPage/Log/Schedule'

function MyPage() {
  // const [state, setState] = useState(false)

  // function onClickBottomSheetHandler() {
  //   setState(!state)
  // }
  return (
    <>
      <Section>
        <TitleBar title="우리의 일정" hasBackPage />
        <Spacing size="0.5rem" />
        <Calender />
        <Outlet></Outlet>
        <Schedule />
      </Section>
      <Footer />
      {/* {state && (
        <BackDrop onClick={onClickBottomSheetHandler}>
          <BottomSheet height="500px" />
        </BackDrop>
      )} */}
    </>
  )
}

export default MyPage
