import { useDisclosure } from '@chakra-ui/react'
import { MakingCourseContext } from '@context/index'
import { css } from '@emotion/react'
import { useMutation } from '@tanstack/react-query'
import { DateCourse, Place } from '@type/course'
import { useContext, useRef, useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'

import useMeasure from './../../@core/hook/useMeasure'

import AlertModal from '@component/common/AlertModal'
import Button from '@component/common/Button'
import Input from '@component/common/Input'
import TextBox from '@component/common/TextBox'
import KakaoMap from '@component/kakaoMap/KakaoMap'
import FlexBox from '@component/layout/FlexBox'
import Spacing from '@component/layout/Spacing'
import SelectedPlaceList from '@component/pages/SelfMakePage/SelectedPlaceList'

import { requestSubmitDateCourse } from '@api/request/course'

const StickyMap = css`
  position: sticky;
  top: 0px;
`

/** @jsxImportSource @emotion/react */
export default function SelfMakeCurrent() {
  const station = JSON.parse(sessionStorage.getItem('station'))
  const navigate = useNavigate()
  const { dateCourse, setDateCourse } = useContext(MakingCourseContext)

  const [centerView, setCenterView] = useState({
    center: station.point,
    level: 4,
  })
  const onClickMarkerHandler = () => {}
  const onClickPlaceBox = (place: Place) => {
    setCenterView({
      center: { lat: place.points.lat, lng: place.points.lng },
      level: 3,
    })
  }

  const mutation = useMutation({
    mutationFn: (dateCourse: DateCourse) => requestSubmitDateCourse(dateCourse),
    onSuccess: () => {
      navigate('/favorite')
    },
  })
  const { isOpen, onOpen, onClose } = useDisclosure()
  const cancelRef = useRef()
  const { widthState: width, ref } = useMeasure()

  return (
    <>
      {/* 코스 이름 생성 */}
      <FlexBox j="center" d="column" p="10px" w="100%" ref={ref}>
        {/* station */}
        <TextBox typography="t2">{station.stationName}</TextBox>
        <FlexBox a="center" j="space-between">
          {/* courseTitle Input */}
          <FlexBox a="center">
            <TextBox fontWeight="bold">코스명 :</TextBox>
            <Input
              placeholder="생성할 코스 이름"
              width={'60%'}
              onChange={e => {
                setDateCourse({
                  courseTitle: e.target.value,
                  placeList: dateCourse.placeList,
                })
              }}
            />
          </FlexBox>
          <Button
            bgColor="pink400"
            onClick={() => {
              if (
                dateCourse.courseTitle.length > 0 &&
                dateCourse.placeList.length > 0
              )
                mutation.mutate(dateCourse)
              else {
                onOpen()
              }
            }}
          >
            등록
          </Button>
        </FlexBox>
      </FlexBox>
      {/* KakaoMap */}
      <FlexBox css={StickyMap}>
        <KakaoMap
          width="100%"
          height="30vh"
          padding="0px"
          onClickMarkerHandler={onClickMarkerHandler} // 마커 클릭 이벤트에 대한 콜백
          placeList={dateCourse.placeList} // 장소 조회 api의 응답값
          centerView={centerView} // 지하철역의 좌표가 카카오맵의 중심 좌표가 된다
          hasMarker
          hasLine
          mode="current"
        />
      </FlexBox>
      <Spacing size="7px" />
      {/* 현재까지 저장된 dateCourse */}
      <SelectedPlaceList
        placeList={dateCourse.placeList}
        onClickPlaceBox={onClickPlaceBox}
      />
      <Spacing size="50px" />
      {/* Button */}
      <Link to="../search">
        <Button css={{ width: width, position: 'fixed', bottom: '4rem' }}>
          장소 추가하러 가기
        </Button>
      </Link>
      <AlertModal
        isOpen={isOpen}
        onClose={onClose}
        header="정보가 부족해요 ㅠ.ㅠ"
        body="제목과 코스를 1개 이상 추가해주세요."
        footer="닫기"
        cancelRef={cancelRef}
      />
    </>
  )
}
