export function getJWT(key: string) {
  return sessionStorage.getItem(key)
}
