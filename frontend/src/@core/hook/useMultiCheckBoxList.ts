import { useState } from 'react'

/**
 * @summary : 질문리스트, 최대 선택 개수를 입력받아 state로 관리할 수 있는 훅입니다.
 * @param checkboxes : 질문 리스트 배열
 * @param selectLimit : 최대 선택 개수
 * @returns
 */
export default function useMultiCheckBoxList<T>(
  checkboxes: T[],
  selectLimit: number,
) {
  //특정 순서의 질문이 선택되었는가?
  const [checkboxStates, setCheckboxStates] = useState<boolean[]>(
    Array(checkboxes.length).fill(false),
  )

  const [selectCount, setSelectCount] = useState<number>(0)

  function onChangeHandler(categoryNumber: number) {
    // 선택 헤제하는 Case
    if (checkboxStates[categoryNumber]) {
      const temp = [...checkboxStates]
      temp[categoryNumber] = !checkboxStates[categoryNumber]
      setCheckboxStates(temp)
      setSelectCount(prev => prev - 1)
    }
    // 선택 추가하는 Case
    else {
      if (selectCount >= selectLimit) return
      const temp = [...checkboxStates]
      setSelectCount(prev => prev + 1)
      temp[categoryNumber] = !checkboxStates[categoryNumber]
      setCheckboxStates(temp)
    }
  }

  return { checkboxStates, onChangeHandler, selectCount }
}
