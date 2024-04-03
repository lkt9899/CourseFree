import TitleBar from '@component/common/TitleBar'
import Footer from '@component/layout/Footer'
import Section from '@component/layout/Section'
import Setting from '@component/pages/MyPage/setting/Setting'

function MyPage() {
  // const [state, setState] = useState(false);

  // function onClickBottomSheetHandler() {
  //   setState(!state);
  // }

  return (
    <>
      <Section>
        <TitleBar title="설정" hasBackPage />
        <Setting />
      </Section>
      <Footer />
      {/* {state && (
        <BackDrop onClick={onClickBottomSheetHandler}>
          <BottomSheet height="500px" />
        </BackDrop>
      )} */}
    </>
  );
}

export default MyPage;
