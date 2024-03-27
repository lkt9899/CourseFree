import { useEffect, useState } from 'react'

interface debounceProps<T> {
  value: T
  delay: number
}
/**
 *
 * @param value : 변화하는 데이터 값입니다.
 * @param delay : 디바운싱을 적용할 딜레이 시간입니다.
 * @returns
 */
function useDebounce<T>({ value, delay }: debounceProps<T>): T {
  const [debouncedValue, setDebouncedValue] = useState(value)

  useEffect(() => {
    const handler = setTimeout(() => {
      setDebouncedValue(value)
    }, delay)

    return () => {
      clearTimeout(handler)
    }
  }, [value])

  return debouncedValue
}

export default useDebounce
