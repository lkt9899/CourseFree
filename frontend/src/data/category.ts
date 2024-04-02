export const categoryKey = {
  RESTAURANT: '식당',
  CAFE: '카페',
  THEATER: '영화관',
  BOARD_CAFE: '보드게임',
  CARTOON_CAFE: '만화카페',
  PC_ROOM: 'PC방',
  ESCAPE_ROOM: '방탈출',
  MALL: '쇼핑몰',
}
export const category = Object.keys(categoryKey)
export type Categories = keyof typeof categoryKey
