import { Avatar } from '@chakra-ui/react'
import styled from '@emotion/styled'
import { useRef } from 'react'
import { useParams } from 'react-router-dom'

import Input from '@component/common/Input'
import TextBox from '@component/common/TextBox'

import useInput from '@hook/useInput'

const Container = styled.div`
  width: 100%;
  /* background-color: blue; */
  display: flex;
  position: fixed;
  align-items: center;
  justify-content: space-around;
  padding: 5px;
  bottom: 0px; // 높이의 100% 만큼 마이너스
  border-top: 1px solid #f3f3f3;
  background-color: white;
  min-height: 3rem;
`
type Props = {
  mutate: any
}
export default function CommentInput({ mutate }: Props) {
  const { id } = useParams()
  const { state, onChange, reset } = useInput<string>({ data: '' })
  const ref = useRef(null)
  return (
    <Container
      onClick={e => {
        e.stopPropagation()
      }}
    >
      <Avatar size="sm" />
      <Input
        width="80%"
        placeholder="댓글을 입력하세요."
        onChange={onChange}
        ref={ref}
      />
      <button>
        <TextBox
          typography="t6"
          color="primary"
          fontWeight={'bold'}
          onClick={() => {
            const idd = parseInt(id)
            const data = { postId: idd, content: state }
            ref.current.value = ''
            reset()
            mutate(data)
          }}
        >
          등록
        </TextBox>
      </button>
    </Container>
  )
}
