import TitleBar from '@component/common/TitleBar'
import Footer from '@component/layout/Footer'
import Section from '@component/layout/Section'
import SearchBar from '@component/common/SearchBar'
import SearchResult from '@component/pages/SearchPage/SearchResult'

export default function SearchPage() {
    return (
        <>
            <Section>
                <TitleBar title="검색" hasBackPage />
                <SearchBar placeholder="검색어를 입력해주세요." />
                <SearchResult></SearchResult>
            </Section>
            <Footer />
        </>
    )
}
