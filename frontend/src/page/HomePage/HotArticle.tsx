import { ArticleThumbnail } from '@type/article'
import 'swiper/css'
import 'swiper/css/pagination'
import { Pagination } from 'swiper/modules'
import { Swiper, SwiperSlide } from 'swiper/react'

import FullPageLoading from '@component/common/FullPageLoading'
import FlexBox from '@component/layout/FlexBox'
import ArticleCard from '@component/pages/HomePage/ArticleCard'

import { useHotArticleQuery } from '@hook/ReactQuery/useHotArticleQuery'

export default function HotArticle() {
  // get 요청으로 아티클 받아오기
  const { articleThumbnails, isLoading } = useHotArticleQuery()
  if (isLoading) return <FullPageLoading />
  return (
    <FlexBox h="90%">
      <Swiper
        modules={[Pagination]}
        spaceBetween={5}
        pagination={{ clickable: true, dynamicBullets: true }}
        slidesPerView={1}
        centeredSlides={true}
      >
        {articleThumbnails.data.map((articleThumbnail: ArticleThumbnail) => {
          return (
            <SwiperSlide>
              <ArticleCard data={articleThumbnail} />
            </SwiperSlide>
          )
        })}
      </Swiper>
    </FlexBox>
  )
}
