import { useState } from 'react';
import { Icon } from '@iconify/react';
import { format, addMonths, subMonths, addDays, isSameMonth, isSameDay } from 'date-fns';
import { useRecoilState } from 'recoil';
import { specialDaysState, selectedDateState } from '@recoil/logSpecialDaysAtom';
import { startOfMonth, endOfMonth, startOfWeek, endOfWeek } from 'date-fns';
import {
    Header, IconContainer, CalendarContainer, Container, DayHeader, Cell, RegisterButton,DayHeaderOuter
} from '@styled/component/pages/MyPage/Log/Calander';

const Calendar = () => {
    const [currentMonth, setCurrentMonth] = useState(new Date());
    const [isRegistered, setIsRegistered] = useState(false);
    const [specialDays, setSpecialDays] = useRecoilState(specialDaysState);
    const [selectedDate, setSelectedDate] = useRecoilState(selectedDateState);

    const prevMonth = () => {
        setCurrentMonth(subMonths(currentMonth, 1));
    };

    const nextMonth = () => {
        setCurrentMonth(addMonths(currentMonth, 1));
    };

    const onDateClick = (day: Date) => {
        setSelectedDate(day);
        console.log('Selected date:', day);
        const formattedDate = format(day, 'yyyy-MM-dd');
        if (specialDays[formattedDate]) {
            console.log('기록이 있습니다');
        }
        if (specialDays[formattedDate]) {
            setIsRegistered(true);
        } else {
            setIsRegistered(false);
        }
    };


    const toggleRegistration = () => {
        const formattedDate = format(selectedDate, 'yyyy-MM-dd');
        if (isRegistered) {
            const updatedSpecialDays = { ...specialDays };
            delete updatedSpecialDays[formattedDate];
            setSpecialDays(updatedSpecialDays);
            setIsRegistered(false);
        } else {
            setSpecialDays({ ...specialDays, [formattedDate]: 'light-red' });
            setIsRegistered(true);
        }
    };

    return (
        <Container>
            <CalendarContainer>
                <Header>
                    <span>
                        {format(currentMonth, 'yyyy')}년
                        <span>  {format(currentMonth, 'M')}월</span>
                    </span>
                    <IconContainer>
                        <Icon icon="bi:arrow-left-circle-fill" onClick={prevMonth} style={{ marginRight: '10px' }} />
                        <Icon icon="bi:arrow-right-circle-fill" onClick={nextMonth} />
                    </IconContainer>
                </Header>
                <DayHeaderOuter>
                    <DayHeader>
                        {['일', '월', '화', '수', '목', '금', '토'].map((day, index) => (
                            <div key={index}>{day}</div>
                        ))}
                    </DayHeader>
                </DayHeaderOuter>
                <RenderCells
                    currentMonth={currentMonth}
                    selectedDate={selectedDate}
                    onDateClick={onDateClick}
                    specialDays={specialDays}
                />
            </CalendarContainer>
            <RegisterButton onClick={toggleRegistration} isRegistered={isRegistered}>
                {isRegistered ? '삭제' : '등록'}
            </RegisterButton>
        </Container>
    );
};

const RenderCells = ({ currentMonth, selectedDate, onDateClick, specialDays }) => {
    const today = new Date(); // 오늘 날짜
    const monthStart = startOfMonth(currentMonth);
    const monthEnd = endOfMonth(monthStart);
    const startDate = startOfWeek(monthStart);
    const endDate = endOfWeek(monthEnd);

    const rows = [];
    let days = [];
    let day = startDate;
    let formattedDate = '';

    while (day <= endDate) {
        for (let i = 0; i < 7; i++) {
            formattedDate = format(day, 'd');
            const cloneDay = day;
            let className = '';
            if (!isSameMonth(day, monthStart)) {
                className = 'disabled';
            } else if (isSameDay(day, selectedDate)) {
                className = 'selected';
            } else {
                className = specialDays[format(day, 'yyyy-MM-dd')] || '';
            }
            // 오늘 날짜인 경우 className에 today를 추가하여 스타일을 적용
            if (isSameDay(day, today)) {
                className += ' today';
            }
            days.push(
                <Cell
                    className={`cell ${className}`}
                    key={day}
                    onClick={() => onDateClick(cloneDay)}
                >
                    <span>{formattedDate}</span>
                </Cell>
            );
            day = addDays(day, 1);
        }
        rows.push(<div className="row" style={{ display: 'flex', flexDirection: 'row' }}>{days}</div>);
        days = [];
    }
    return <div className="body">{rows}</div>;
};

export default Calendar;
