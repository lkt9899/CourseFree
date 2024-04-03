import { useCallback, useEffect, useRef } from 'react'

type IntersectHandler = (
  entry: IntersectionObserverEntry,
  observer: IntersectionObserver,
) => void

export const useObserver = (
  onIntersect: IntersectHandler,
  options?: IntersectionObserverInit,
) => {
  // 관찰할 타겟
  const ref = useRef<HTMLDivElement>(null)
  //IO에 callback으로 들어갈 함수
  const callback = useCallback(
    (entries: IntersectionObserverEntry[], observer: IntersectionObserver) => {
      entries.forEach(entry => {
        if (entry.isIntersecting) onIntersect(entry, observer)
      })
    },
    [onIntersect],
  )

  useEffect(() => {
    if (!ref.current) return
    // 옵저버 생성
    const observer = new IntersectionObserver(callback, options)
    // ref객체 관찰 시작
    observer.observe(ref.current)
    // unmount시 관찰 종료
    return () => observer.disconnect()
  }, [ref, options, callback])

  return ref
}
