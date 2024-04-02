import { MakingCourseContext } from '@context/index'
import { DateCourse } from '@type/course'
import { useState } from 'react'
import { Outlet } from 'react-router-dom'

import TitleBar from '@component/common/TitleBar'

export default function SelfMakePage() {
  // 장소 추가 클릭 시 페이지 전환되는데, 데이터를 계속 유지해야하므로 context API사용하여 상태 관리
  const [dateCourse, setDateCourse] = useState<DateCourse>({
    courseTitle: '',
    placeList: [],
  })
  return (
    <>
      <MakingCourseContext.Provider value={{ dateCourse, setDateCourse }}>
        <TitleBar hasBackPage hasBottomLine title="직접 만들기" />
        {/* <Spacing size="10px" /> */}

        <Outlet />
      </MakingCourseContext.Provider>
    </>
  )
}
