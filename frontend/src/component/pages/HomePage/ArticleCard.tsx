import { ArticleThumbnail } from '@type/article'
import { useNavigate } from 'react-router-dom'

import TextBox from '@component/common/TextBox'
import BackDrop from '@component/layout/Backdrop'

import {
  Container,
  Content,
} from '@styled/component/pages/HomePage/ArticleCard'

interface ArticleCard {
  data: ArticleThumbnail
}
export default function ArticleCard({ data }: ArticleCard) {
  const navigate = useNavigate()

  return (
    <Container
      img={data.imageUrl}
      onClick={() => {
        // postId를 실어서 링크 이동
        navigate(`/article/${data.postId}`)
      }}
    >
      <BackDrop opacity={0.5} />
      <Content>
        <TextBox display="flex" typography="t3" fontWeight="bold" color="white">
          {data.title}
        </TextBox>
      </Content>
    </Container>
  )
}
