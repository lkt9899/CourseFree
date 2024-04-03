export interface ArticleThumbnail {
  postId: number
  title: string
  imageUrl: string
}
export interface PostContentInfo {
  placeName: string
  placeImageUrl: string
  url: string
  title: string
  content: string
}
export interface ArticleDetail {
  postId: number
  postTitle: string
  memberImageUrl: string
  memberNickname: string
  postContentInfoList: PostContentInfo[]
}
