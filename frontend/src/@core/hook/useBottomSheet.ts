import { useEffect, useRef } from 'react'
import { useSetRecoilState } from 'recoil'

import { BottomSheetAnimation } from '@util/animation'

import { bottomSheetShowState } from '@recoil/bottomSheetAtom'

/**
 * Handle Container의 터치로 bottomsheet 전체의 높이를 통제한다.
 * touchStart
 *  터치한 순간의 y좌표를 기록한다 = startY
 * touchMove
 *  터치 상태로 움직이면 이벤트 객체에 현재 bottomsheet의 y좌표가 기록된다.
 *  현재 내 손의 y좌표와 initY의 차이를 비교하고 그만큼 bottomsheet의 height를 낯추거나, 높인다.
 *  initY보다 더 높은 위치로는 터치 불가능.
 * touchEnd
 *  터치를 멈출 때의 y좌표를 통해, 원래의 height로 돌아갈지, height를 0으로 만들지 판단.
 */

export function useBottomSheet() {
  // 변수 선언 및 state 관리
  const sheet = useRef<HTMLDivElement>(null) // bottomSheet를 참조할 Ref객체
  const handle = useRef<HTMLDivElement>(null) // bottomSheetHandler (실제로 터치를 통해 높이를 조절하는 영역)를 참조할 Ref객체
  const setBottomSheetState = useSetRecoilState(bottomSheetShowState) // bottomSheet의 open/close를 조절할 전역 상태

  // DOM노드 참조 및 이벤트핸들러 등록
  useEffect(() => {
    // Effect 내부 변수 선언
    let startY = null // handleTouch 이벤트 발생 시 할당 될 터치 시작 Y좌표 값 선언만 해놓기
    const sheetRef = sheet.current // 실제 참조중인 bottomSheet 컴포넌트 태그
    const handleRef = handle.current // 실제 참조중인 bottomSheetHandle 컴포넌트 태그
    const initHeight = sheetRef.offsetHeight // bottomSheet의 초기 높이값

    // 컴포넌트가 150ms에 걸쳐 위로 올라오는 애니메이션 적용
    sheetRef.animate(
      BottomSheetAnimation.up(initHeight),
      BottomSheetAnimation.options,
    )

    const handleTouchStart = (e: TouchEvent) => {
      // 터치 시작!
      e.stopPropagation()
      startY = e.touches[0].clientY // 터치가 일어난 지점의 Y좌표
    }

    const handleTouchMove = (e: TouchEvent) => {
      e.stopPropagation()
      // sheet 위치 갱신.
      const currentY = e.touches[0].clientY // 현재 사용자의 손이 스크롤되고 있는 Y좌표
      const diff = currentY - startY
      if (diff >= 0) {
        // 스크롤 시작
        sheetRef.style.setProperty('transform', `translateY(${diff}px)`) // 스크롤된 만큼 bottomSheet의 Y축 위치를 조절
      }
    }

    const handleTouchEnd = (e: TouchEvent) => {
      e.stopPropagation()
      const endY = e.changedTouches[0].clientY

      // 일정 값 이상으로 스크롤이 안내려가면 다시 원점 복귀
      if (endY < 400) sheetRef.style.setProperty('transform', `translateY(0px)`)
      // 일정 값 이하로 스크롤이 내려가면 아래로 내려가고 언마운트 처리
      else {
        sheetRef.style.setProperty('transform', `translateY(${initHeight}px)`)
        setTimeout(() => {
          setBottomSheetState(false)
        }, 150)
      }
    }

    handleRef.addEventListener('touchstart', handleTouchStart)
    handleRef.addEventListener('touchmove', handleTouchMove)
    handleRef.addEventListener('touchend', handleTouchEnd)

    return () => {
      handleRef.removeEventListener('touchstart', handleTouchStart)
      handleRef.removeEventListener('touchmove', handleTouchMove)
      handleRef.removeEventListener('touchend', handleTouchEnd)
    }
  }, [])

  return { sheet, handle }
}
