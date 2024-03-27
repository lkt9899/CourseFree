import TextBox from '@component/common/TextBox'

type Props = {
  children: string
}

export default function OnBoardingMessage({ children }: Props) {
  return (
    <TextBox typography="t2" fontWeight="bold">
      {children}
    </TextBox>
  )
}
