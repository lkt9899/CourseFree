import kiss from '@asset/namsan.jfif'
import { Pagination } from 'swiper/modules'
import { Swiper, SwiperSlide } from 'swiper/react'

import CourseCard from './CourseCard'

export default function TopFiveCourseCarousel() {
  return (
    <Swiper
      modules={[Pagination]}
      spaceBetween={0}
      pagination={{
        clickable: true,
        dynamicBullets: true,
      }}
      navigation={true}
      slidesPerView={1}
      // centeredSlides={true}
    >
      <SwiperSlide>
        <CourseCard img={kiss} />
      </SwiperSlide>
      <SwiperSlide>
        <CourseCard img={kiss} />
      </SwiperSlide>
      <SwiperSlide>
        <CourseCard img={kiss} />
      </SwiperSlide>
      <SwiperSlide>
        <CourseCard img={kiss} />
      </SwiperSlide>
      <SwiperSlide>
        <CourseCard img={kiss} />
      </SwiperSlide>
      <SwiperSlide>
        <CourseCard img={kiss} />
      </SwiperSlide>
    </Swiper>
  )
}
