// Import Swiper React components
// Import Swiper styles
import { memo } from 'react'
import 'swiper/css'
import { Swiper, SwiperSlide } from 'swiper/react'

import { Categories, category, categoryKey } from './../../data/category'
import CategoryItem from './CategoryItem'

/** @jsxImportSource @emotion/react */
interface Props {
  onClick: (type: Categories) => void
}
export default memo(function CategorySlider({ onClick }: Props) {
  return (
    <Swiper
      style={{ position: 'absolute', top: '10px', zIndex: '2', width: '100%' }}
      spaceBetween={4}
      slidesPerView={4}

      //   centeredSlides={true}
    >
      {category.map((e: Categories) => (
        <SwiperSlide>
          <CategoryItem title={categoryKey[e]} type={e} onClick={onClick} />
        </SwiperSlide>
      ))}
    </Swiper>
  )
})
