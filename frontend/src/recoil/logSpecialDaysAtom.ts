import { atom } from 'recoil';

// 선택된 날짜 상태
export const selectedDateState = atom<Date>({
  key: 'selectedDateState',
  default: new Date(),
});

// 특별한 날짜 상태
export const specialDaysState = atom<{
  [date: string]: 'light-red' | 'light-yellow';
}>({
  key: 'specialDaysState',
  default: {
    '2024-03-05': 'light-yellow',
    '2024-03-10': 'light-yellow',
    '2024-03-27': 'light-yellow',
  },
});
