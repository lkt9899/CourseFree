import { useState, useEffect, ReactNode } from 'react';
import { useRecoilValue } from 'recoil';
import { selectedDateState, specialDaysState } from '@recoil/logSpecialDaysAtom';
import { format } from 'date-fns';
import { JSX } from 'react/jsx-runtime';
import { Container, DataSet, Photo, Content, ContentUp, ContentDown, DateText, Place, Button, ExpandedContent, ExpandedPlace, ExpandedName, ExpandedBody, Expanded, ExpandedTable, ExpandedTd, ExpandedTh, NoSchedule } from '@styled/component/pages/MyPage/Log/Schedule'


const Schedule = () => {
    const selectedDate = useRecoilValue(selectedDateState); // Recoil 상태 사용
    const specialDays = useRecoilValue(specialDaysState); // specialDays 상태 사용

    const noSchedule = '일정이 없습니다';

    // 현재 선택된 날짜를 문자열 형식으로 변환
    const formattedSelectedDate = format(selectedDate, 'yyyy-MM-dd');

    const station = '역삼역';
    // 일정이 있는 경우에 사용할 더미 장소들
    const places = [
        { name: '밸런스포케', type: '포케', hours: '09:00 - 22:00', phone: '010-1234-5678', link: 'https://example.com/place1' },
        { name: '강남 파이낸스 센터 빌딩', type: '쇼핑몰', hours: '09:00 - 18:00', phone: '010-5678-1234', link: 'https://example.com/place2' },
        { name: '카페 413 프로젝트', type: '카페', hours: '10:00 - 20:00', phone: '010-9876-5432', link: 'https://example.com/place3' }
    ];

    // 펼치기/접기 상태 관리
    const [isExpanded, setIsExpanded] = useState(false);

    // 펼치기/접기 버튼 클릭 핸들러
    const toggleExpand = () => {
        setIsExpanded(!isExpanded);
    };

    // 현재 선택된 날짜에 대한 스케줄 표시
    const scheduleInfo = specialDays[formattedSelectedDate];
    let scheduleContent: string | number | boolean | Iterable<ReactNode> | JSX.Element;

    useEffect(() => {
        // 날짜가 변경될 때마다 펼쳐진 상태를 초기값으로 설정
        setIsExpanded(false);
    }, [selectedDate]); // selectedDate가 변경될 때 useEffect가 실행됨


    if (scheduleInfo) {
        // 일정이 있는 경우: 더미 데이터로 사진을 설정
        const photoUrl = '더미 사진 URL';

        scheduleContent = (
            <Container>
                <DateText>{formattedSelectedDate}</DateText>
                <DataSet>
                    <Photo src={photoUrl} alt="일정 사진" />
                    <Content>
                        <ContentUp>
                            {station} {'데이트'}
                        </ContentUp>
                        <ContentDown>
                            {places.map((place, index) => (
                                <div key={index}>
                                    <Place>
                                        {place.name}
                                    </Place>
                                </div>
                            ))}
                        </ContentDown>
                    </Content>
                </DataSet>
                <Button onClick={toggleExpand}>
                    {isExpanded ? '접기' : '펼치기'}
                </Button>
                {isExpanded && (
                    <ExpandedContent>
                        {places.map((place, index) => (
                            <div key={index}>
                                <ExpandedPlace onClick={() => window.open(place.link, '_blank')}>
                                    <Photo src={photoUrl} alt="일정 사진" />
                                    <ExpandedBody>
                                        <ExpandedName>
                                            {place.name}
                                        </ExpandedName>
                                        <Expanded>
                                            <ExpandedTable>
                                                <tbody>
                                                    <tr>
                                                        <ExpandedTh>종류</ExpandedTh>
                                                        <ExpandedTd>{place.type}</ExpandedTd>
                                                    </tr>
                                                    <tr>
                                                        <ExpandedTh>영업시간</ExpandedTh>
                                                        <ExpandedTd>{place.hours}</ExpandedTd>
                                                    </tr>
                                                    <tr>
                                                        <ExpandedTh>전화번호</ExpandedTh>
                                                        <ExpandedTd>{place.phone}</ExpandedTd>
                                                    </tr>
                                                </tbody>
                                            </ExpandedTable>
                                        </Expanded>
                                    </ExpandedBody>
                                </ExpandedPlace>
                            </div>
                        ))}
                    </ExpandedContent>
                )}
            </Container>
        );
    } else {
        // 일정이 없는 경우
        scheduleContent = (
            <NoSchedule>
            <p>{noSchedule}</p>
            </NoSchedule>
        );
    }

    return (
        <div>
            {scheduleContent}
        </div>
    );

};

export default Schedule;
