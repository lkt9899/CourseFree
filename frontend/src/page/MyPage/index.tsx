import { useState } from 'react'
import { Outlet } from 'react-router-dom'

import BackDrop from '@component/layout/Backdrop'
import BottomSheet from '@component/layout/BottomSheet'
import Footer from '@component/layout/Footer'
import Section from '@component/layout/Section'
import Spacing from '@component/layout/Spacing'
import TitleBar from '@component/common/TitleBar'
import Menu from '@component/pages/MyPage/Menu'
import Users from '@component/pages/MyPage/Users'



function MyPage() {
  const [state, setState] = useState(false)

  function onClickBottomSheetHandler() {
    setState(!state)
  }
  return (
    <>
      <Section>
      <TitleBar title="마이페이지" hasBackPage />
        <Users></Users>
        <Spacing size="2rem" />
        <Menu></Menu>
        <Outlet></Outlet>
      </Section>
      <Footer />
      {state && (
        <BackDrop onClick={onClickBottomSheetHandler}>
          <BottomSheet height="500px" />
        </BackDrop>
      )}
    </>
  )
}

export default MyPage
