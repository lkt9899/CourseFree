import { BaseOnClickType } from '@type/eventFunction'

import { ArticleFilterType } from './ArticleViews'

import TextBox from '@component/common/TextBox'
import FlexBox from '@component/layout/FlexBox'
import Spacing from '@component/layout/Spacing'

interface ArticleFilterNavigation {
  filter: ArticleFilterType
  onClick: BaseOnClickType
}

export default function ArticleFilterNavigation({
  filter,
  onClick,
}: ArticleFilterNavigation) {
  return (
    <FlexBox>
      {filter === 'recent' ? (
        <TextBox
          typography="t6"
          color="black"
          fontWeight="bold"
          onClick={onClick}
        >
          최신순
        </TextBox>
      ) : (
        <TextBox typography="t6" color="gray" onClick={onClick}>
          최신순
        </TextBox>
      )}

      <Spacing size="1rem" d="horizontal" />
      {filter === 'wish-list' ? (
        <TextBox
          typography="t6"
          color="black"
          fontWeight="bold"
          onClick={onClick}
        >
          조회순
        </TextBox>
      ) : (
        <TextBox typography="t6" color="gray" onClick={onClick}>
          조회순
        </TextBox>
      )}
    </FlexBox>
  )
}
