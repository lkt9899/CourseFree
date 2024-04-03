import { Outlet } from 'react-router-dom'

import TitleBar from '@component/common/TitleBar'
import Footer from '@component/layout/Footer'
import Section from '@component/layout/Section'
import Map from '@component/pages/MyPage/Memory/Map'

function MyPage() {
  // const [state, setState] = useState(false)

  // function onClickBottomSheetHandler() {
  //   setState(!state)
  // }
  return (
    <>
      <Section>
        <TitleBar title="우리의 기록" hasBackPage />
        <Map />
        <Outlet></Outlet>
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
