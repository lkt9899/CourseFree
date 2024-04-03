export function debounce(func, timeout = 300) {
  // 클로저를 활용해 timer 변수에 계속 접근 가능
  let timer
  return (...args) => {
    clearTimeout(timer)
    timer = setTimeout(() => {
      func.apply(this, args) //args를 배열 형태로 전달
    }, timeout)
  }
}
