
import { useBottomSheet } from '@hook/useBottomSheet'

import TextBox from '@component/common/TextBox'
import {
  Container,
  HandleBar,
  HandleBarContainer,
} from '@styled/component/layout/BottomSheet'
import { BottomSheet } from '@type/BottomSheet'

/**
 * @param children: bottomSheet 내부에 담길 다양한 컨텐츠의 컴포넌트
 * @param title : bottomSheet에 담길 컨텐츠의 제목
 * @param visibleHandler : bottomSheet의 렌더링 state를 변경하는 setter 함수
 * @param backDrop : backDrop 기능을 가질지의 유무
 */
export default function BottomSheet({
  children,
  title = 'bottomSheet',
  visibleHandler,
  backDrop = true
}: BottomSheet) {
  const  { sheet, handle} = useBottomSheet(visibleHandler)


  return (
   <BottomSheetPortal> 
   {backDrop &&  <BackDrop onClick={visibleHandler}> 
    </BackDrop> }
      <Container ref={sheet}>
    <HandleBarContainer ref={handle} >
      <HandleBar />
      <TextBox typography="t7" fontWeight="bold">
        {title}
      </TextBox>
    </HandleBarContainer>
    {children}
  </Container>
  </BottomSheetPortal>
  )
}

import ReactDom from 'react-dom'
import BackDrop from './Backdrop'

const BottomSheetPortal = ({ children }) => {
  const el = document.getElementById('bottomSheet')
  return ReactDom.createPortal(children, el)
}

