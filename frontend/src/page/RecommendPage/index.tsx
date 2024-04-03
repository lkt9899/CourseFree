import { Outlet } from 'react-router-dom'

import TitleBar from '@component/common/TitleBar'
import Spacing from '@component/layout/Spacing'

export default function RecommendPage() {
  return (
    <>
      <TitleBar hasBackPage hasBottomLine title="추천 받기" />
      <Spacing size="20px" />
      <Outlet />
    </>
  )
}
