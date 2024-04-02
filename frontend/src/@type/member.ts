import { Categories } from './preference'

export interface Token {}

export interface OnBoardingQuestion {
  text: string
  category: string
}
export interface UserFavoriteInfo {
  first: Categories
  second: Categories
  third: Categories
}

export interface UserData {
  nickName: string
  gender: string
  preference: UserFavoriteInfo
}
export interface SubmitUserData {
  profileInfoRequest: UserData
}
