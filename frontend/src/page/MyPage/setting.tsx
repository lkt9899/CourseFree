import { useState} from 'react';

import BackDrop from '@component/layout/Backdrop';
import BottomSheet from '@component/layout/BottomSheet';
import Footer from '@component/layout/Footer';
import Section from '@component/layout/Section';
import TitleBar from '@component/common/TitleBar';
import Setting from '@component/pages/MyPage/setting/Setting';


function MyPage() {
  const [state, setState] = useState(false);

  function onClickBottomSheetHandler() {
    setState(!state);
  }

  return (
    <>
      <Section>
        <TitleBar title="설정" hasBackPage />
        <Setting />
      </Section>
      <Footer />
      {state && (
        <BackDrop onClick={onClickBottomSheetHandler}>
          <BottomSheet height="500px" />
        </BackDrop>
      )}
    </>
  );
}

export default MyPage;
