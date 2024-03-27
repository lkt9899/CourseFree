import { SignUpStepContext } from '@context/index'
import { useState } from 'react'
import { Outlet } from 'react-router-dom'

import ProgressBar from '@component/common/ProgressBar'
import TitleBar from '@component/common/TitleBar'
import Section from '@component/layout/Section'
import Spacing from '@component/layout/Spacing'

/** @jsxImportSource @emotion/react */

export default function OnBoardingPage() {
  const [step, setStep] = useState(1)
  const [nickname, setNickname] = useState('')
  return (
    <SignUpStepContext.Provider
      value={{ step, setStep, nickname, setNickname }}
    >
      <Section>
        <TitleBar title="회원가입" hasBackPage />
        <ProgressBar max={3} value={step} width={'100%'} height={'10px'} />
        <Spacing />
        <Outlet />
      </Section>
    </SignUpStepContext.Provider>
  )
}
