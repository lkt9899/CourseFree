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
  selectCategory: Categories
}
export default memo(function CategorySlider({
  onClick,
  selectCategory,
}: Props) {
  return (
    <Swiper
      style={{ position: 'absolute', top: '10px', zIndex: '2', width: '100%' }}
      spaceBetween={2}
      slidesPerView={6}

      //   centeredSlides={true}
    >
      {category.map((e: Categories) => (
        <SwiperSlide>
          <CategoryItem
            title={categoryKey[e]}
            type={e}
            onClick={onClick}
            isSelected={e === selectCategory}
          />
        </SwiperSlide>
      ))}
    </Swiper>
  )
})
