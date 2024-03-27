import { useState } from 'react'

/**
 * input 태그의 값을 state로 관리하는 방법
 * @param state
 */
function useInput<T>(data: T) {
  const [state, setState] = useState<T | string>(data)

  function onChange(e: React.ChangeEvent<HTMLInputElement>) {
    if (e.target) {
      setState(e.target.value)
    }
  }
  function reset() {
    setState('')
  }

  return { state, onChange, reset }
}
export default useInput
