import { SignUpStepContext } from '@context/index'
import { css } from '@emotion/react'
import { useContext } from 'react'
import { useNavigate } from 'react-router-dom'

import Button from '@component/common/Button'
import Input from '@component/common/Input'
import TextBox from '@component/common/TextBox'
import ValidMessage from '@component/common/ValidMessage'
import FlexBox from '@component/layout/FlexBox'
import Spacing from '@component/layout/Spacing'

import useNickNameValidCheck from '@hook/Member/useNickNameValidCheck'
import useDebounce from '@hook/useDebounce'
import useInput from '@hook/useInput'

export const BottomFixedButtonStyle = css`
  position: fixed;
  max-width: 450px;
  bottom: 0px;
`
/** @jsxImportSource @emotion/react */
export default function SetNickName() {
  const navigate = useNavigate()
  const { setStep, setNickname } = useContext(SignUpStepContext)
  const { state: name, onChange: onChangeNameHandler } = useInput<string>({
    data: '',
  })
  const debouncedName = useDebounce<string>({ value: name, delay: 500 })
  const [isValidNickName] = useNickNameValidCheck(debouncedName)

  /**
   * 버튼 클릭 시, 다음 페이지로 넘어갑니다
   */

  return (
    <>
      <FlexBox p="10px" d="column" w="100%">
        <TextBox typography="t2" fontWeight="bold">
          닉네임을 입력해주세요.
        </TextBox>
        <Spacing size="7px" />
        <TextBox typography="t6" color="gray">
          다른 사용자에게 보여지게 되는 별명입니다.
        </TextBox>
        <Spacing />
        <Input
          value={name}
          autoFocus
          placeholder="닉네임을 입력해주세요."
          onChange={onChangeNameHandler}
        />
        <Spacing />
        <ValidMessage
          valid="사용 가능한 닉네임입니다."
          inValid="이미 존재하는 닉네임입니다."
          isValid={isValidNickName}
          show={name.length > 0}
        />
      </FlexBox>

      <Button
        disabled={!isValidNickName || debouncedName.length === 0}
        full
        css={BottomFixedButtonStyle}
        onClick={() => {
          setNickname(debouncedName)
          setStep(prev => prev + 1)
          navigate(`./info`)
        }}
      >
        확인
      </Button>
    </>
  )
}
