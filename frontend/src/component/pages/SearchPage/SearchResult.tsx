import { useState } from 'react';
import { Container, Photo, Results, ResultTitle, ResultNav, ResultNavLeft, ResultNavRight, ResultBody, ResultTable, ResultTd, ResultTh, LinkTo } from '@styled/component/pages/SearchPage/SearchResult';


const SearchResult = () => {
    const [selectedOption, setSelectedOption] = useState('최신순');

    // 일정이 있는 경우에 사용할 더미 장소들
    const places = [
        { title: '포천 드라이브 코스', writer: '주말에 뭐하지', link: 'https://example.com/place1' },
        { title: '서울 근교 대형카페', writer: 'ZUMO', link: 'https://example.com/place2' },
        { title: '일산으로 가볼까?', writer: '역삼동 정현규', link: 'https://example.com/place3' }
    ];

    // 일정이 있는 경우: 더미 데이터로 사진을 설정
    const photoUrl = '더미 사진 URL';

    let scheduleContent = null; // Initialize scheduleContent

    if (places.length > 0) {
        scheduleContent = (
            <Container>
                <ResultNav>
                    <ResultNavLeft>

                        <p
                            style={{ color: selectedOption === '최신순' ? 'black' : '#ccc', cursor: 'pointer', marginRight: '10px', fontWeight: selectedOption === '최신순' ? 'bold' : 'normal' }}
                            onClick={() => setSelectedOption('최신순')}
                        >
                            최신순
                        </p>
                        <p
                            style={{ color: selectedOption === '인기순' ? 'black' : '#ccc', cursor: 'pointer', marginRight: '10px', fontWeight: selectedOption === '인기순' ? 'bold' : 'normal' }}
                            onClick={() => setSelectedOption('인기순')}
                        >
                            인기순
                        </p>
                        <p
                            style={{ color: selectedOption === '북마크순' ? 'black' : '#ccc', cursor: 'pointer', marginRight: '10px', fontWeight: selectedOption === '북마크순' ? 'bold' : 'normal' }}
                            onClick={() => setSelectedOption('북마크순')}
                        >
                            북마크순
                        </p>
                    </ResultNavLeft>
                    <ResultNavRight>
                        <p>검색 결과 {places.length} 건</p>
                    </ResultNavRight>
                </ResultNav>

                {places.map((place, index) => (
                    <div key={index}>
                        <Results onClick={() => window.open(place.link, '_blank')}>
                            <Photo src={photoUrl} alt="일정 사진" />
                            <ResultBody>
                                <ResultTitle>
                                    {place.title}
                                </ResultTitle>
                                <ResultTable>
                                    <tbody>
                                        <tr>
                                            <ResultTh>작성자 :</ResultTh>
                                            <ResultTd>{place.writer}</ResultTd>
                                        </tr>
                                    </tbody>
                                </ResultTable>
                                <LinkTo>
                                    바로가기 {'->'}
                                </LinkTo>
                            </ResultBody>
                        </Results>
                    </div>
                ))}
            </Container>
        );
    }

    return (
        <div>
            {scheduleContent}
        </div>
    );
};

export default SearchResult;
