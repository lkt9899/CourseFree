import { css } from '@emotion/react'
import { useNavigate } from 'react-router-dom'
import { useRecoilValue } from 'recoil'

import TextBox from '@component/common/TextBox'
import FlexBox from '@component/layout/FlexBox'

import { recommendedCourse } from '@recoil/recommendedCourseAtom'

const courseStyle = css`
  cursor: pointer;
`
/** @jsxImportSource @emotion/react */
export default function RecommendList() {
  // 리코일에서 코스 불러오기
  const courseList = useRecoilValue(recommendedCourse)
  const navigate = useNavigate()
  return (
    <>
      <FlexBox d="column" p="10px" a="center">
        {/* 코스1 */}
        <FlexBox
          css={courseStyle}
          d="column"
          p="15px"
          a="center"
          onClick={() => {
            navigate('../result', {
              state: {
                courseId: courseList.firstCourseId,
              },
            })
          }}
        >
          <TextBox typography="t3" fontWeight="bold">
            💘 당신만을 위한 성향 기반 맞춤 코스 💘
          </TextBox>
          <TextBox typography="t6" color="gray">
            사용자의 성향 유사도를 통해 추천된 코스입니다.
          </TextBox>
        </FlexBox>
        {/* 코스 2 */}
        <FlexBox
          css={courseStyle}
          d="column"
          p="15px"
          onClick={() => {
            navigate('../result', {
              state: {
                courseId: courseList.secondCourseId,
              },
            })
          }}
        >
          <TextBox typography="t3" fontWeight="bold">
            ⌚ J들을 위한 효율 백배 코스 ⌚
          </TextBox>
          <TextBox typography="t6" color="gray">
            가장 효율적인 동선을 고려해 추천된 코스입니다.
          </TextBox>
        </FlexBox>
        {/* 코스3 */}
        <FlexBox d="column" p="15px" a="center" css={courseStyle}>
          <TextBox typography="t3" fontWeight="bold">
            🔥 핫플은 못참지! 핫플 모음 코스 🔥
          </TextBox>
          <TextBox typography="t6" color="gray">
            최근 핫한 장소만을 모았어요!
          </TextBox>
        </FlexBox>
      </FlexBox>
    </>
  )
}
