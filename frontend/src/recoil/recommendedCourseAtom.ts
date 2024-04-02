import { atom } from 'recoil'

const recommendedCourse = atom({
  key: 'recommendCourse',
  default: { firstCourseId: 0, secondCourseId: 0 },
})

export { recommendedCourse }
