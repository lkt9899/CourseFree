import { Image } from '@chakra-ui/react'
import { ArticleThumbnail } from '@type/article'
import { Link } from 'react-router-dom'

import TextBox from '@component/common/TextBox'
import FlexBox from '@component/layout/FlexBox'
import Spacing from '@component/layout/Spacing'

type Props = {
  data: ArticleThumbnail
}
export default function ArticleSmallCard({ data }: Props) {
  return (
    <Link to={`/article/${data.postId}`}>
      <FlexBox d="column" a="center" j="center" w="200px">
        <Image
          borderRadius="10px"
          boxSize="150px"
          src={data.imageUrl}
          alt="Dan Abramov"
        />
        <Spacing size="10px" />
        <TextBox textAlign="left" fontWeight="bold" typography="t8">
          {data.title}
        </TextBox>
        {/* <TextBox textAlign="left" typography="t7" color="gray">
          삼성동쭈꾸
        </TextBox> */}
      </FlexBox>
    </Link>
  )
}
