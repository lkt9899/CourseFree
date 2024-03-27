import { OnBoardingQuestion } from '@type/member'

import CheckBoxWithText from './CheckBoxWithText'

import ValidMessage from '@component/common/ValidMessage'
import FlexBox from '@component/layout/FlexBox'
import Spacing from '@component/layout/Spacing'

type UserFavoriteCheckBoxList = {
  onBoardingQuestions: OnBoardingQuestion[]
  categories: boolean[]
  selectCount: number
  onChangeHandler: (index: number) => void
}

export default function UserFavoriteCheckBoxList({
  onBoardingQuestions,
  categories,
  selectCount,
  onChangeHandler,
}: UserFavoriteCheckBoxList) {
  return (
    <FlexBox d="column" p="10px">
      {onBoardingQuestions.map((e, i) => {
        const isChecked = categories[i]
        return (
          <>
            <CheckBoxWithText
              key={e.category}
              color={isChecked ? 'primary' : 'gray'}
              onChange={() => {
                onChangeHandler(i)
              }}
              isChecked={isChecked}
            >
              {e.text}
            </CheckBoxWithText>
            <Spacing />
          </>
        )
      })}
      <ValidMessage
        isValid={selectCount < 3}
        valid="최대 3개의 항목을 고를 수 있습니다."
        inValid="최대 3개의 항목을 고를 수 있습니다."
        show={selectCount === 3}
      />
    </FlexBox>
  )
}
