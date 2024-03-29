import TitleBar from '@component/common/TitleBar'
import Footer from '@component/layout/Footer'
import Section from '@component/layout/Section'
import FavoriteList from '@component/pages/FavoritePage/FavoriteList'

function FavoritePage() {
  return (
    <>
        <Section>
            <TitleBar title="내가 찜한 목록" hasBackPage />
            <FavoriteList/>
        </Section>
        <Footer />
    </>
)
}

export default FavoritePage
