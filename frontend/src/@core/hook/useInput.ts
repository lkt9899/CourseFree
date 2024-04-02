import { useState } from 'react'

import { debounce } from '@util/debounce'

/**
 * input 태그의 값을 state로 관리하는 방법
 * @param state
 */
interface Params<T> {
  data: T
  setDebounce?: boolean
}
function useInput<T>({ data, setDebounce = null }: Params<T>) {
  const [state, setState] = useState<T | string>(data)

  function onChange(e: React.ChangeEvent<HTMLInputElement>) {
    if (e.target) {
      setState(e.target.value)
    }
  }
  function reset() {
    setState('')
  }
  if (setDebounce) {
    const debounceChange = debounce(onChange)
    return { state, debounceChange, reset }
  }
  return { state, onChange, reset }
}
export default useInput
