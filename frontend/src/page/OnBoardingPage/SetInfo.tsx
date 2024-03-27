import { SignUpStepContext } from '@context/index'
import { onBoardingQuestions } from '@data/onBoarding'
import { OnBoardingQuestion, UserData, UserFavoriteInfo } from '@type/member'
import { useContext, useState } from 'react'
import { useNavigate } from 'react-router-dom'

import { BottomFixedButtonStyle } from './SetNickName'

import Button from '@component/common/Button'
import TextBox from '@component/common/TextBox'
import FlexBox from '@component/layout/FlexBox'
import Spacing from '@component/layout/Spacing'
import GenderSelectBox from '@component/pages/OnBoardingPage/SetInfo/GenderSelectBox'
import UserFavoriteCheckBoxList from '@component/pages/OnBoardingPage/SetInfo/UserFavoriteCheckBoxList'

import useMultiCheckBoxList from '@hook/useMultiCheckBoxList'

import { requestUserData } from '@api/request/member'

/** @jsxImportSource @emotion/react */
export default function SetInfo() {
  const navigate = useNavigate()
  const { step, setStep, nickname } = useContext(SignUpStepContext)
  const [gender, setGender] = useState('man')

  const {
    checkboxStates: categories,
    selectCount,
    onChangeHandler,
  } = useMultiCheckBoxList<OnBoardingQuestion>(onBoardingQuestions, 3)

  const sendUserInfo = async categories => {
    const selected = []
    categories.forEach((e, i) => {
      if (e) selected.push(onBoardingQuestions[i]['category'])
    })
    const info = {
      first: selected[0],
      second: selected[1],
      third: selected[2],
    }

    const requestData: UserData = {
      name: nickname,
      gender: gender,
      preference: info as UserFavoriteInfo,
    }

    try {
      const { status } = await requestUserData(requestData)
      if (status === 200) {
        setStep(step + 1)
        navigate('../welcome')
      }
    } catch (error) {
      alert(error)
    }
  }

  const changeGender = (clickedGender: string) => {
    if (gender !== clickedGender) setGender(clickedGender)
  }
  return (
    <>
      <FlexBox p="10px" d="column" w="100%">
        <TextBox typography="t2" fontWeight="bold">
          성별과 식사 취향을 알려주세요! <br />
          3가지 항목을 골라주세요.
        </TextBox>
        <Spacing size="7px" />
        <TextBox typography="t6" color="gray">
          당신에게 꼭 맞는 데이트 코스를 추천해주고 싶어요.
        </TextBox>
      </FlexBox>
      <Spacing />
      <UserFavoriteCheckBoxList
        onBoardingQuestions={onBoardingQuestions}
        categories={categories}
        selectCount={selectCount}
        onChangeHandler={onChangeHandler}
      />

      <GenderSelectBox gender={gender} changeGender={changeGender} />
      <Button
        css={BottomFixedButtonStyle}
        full
        onClick={() => {
          sendUserInfo(categories)
        }}
      >
        확인
      </Button>
    </>
  )
}
