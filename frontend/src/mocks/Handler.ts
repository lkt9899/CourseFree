import API_URI from '@constant/url'
import { HttpResponse, delay, http } from 'msw'

export const handlers = [
  // 이메일 중복 체크 모킹
  http.get(API_URI.BASE + API_URI.VALID_CHECK, async ({ request }) => {
    const { searchParams } = new URL(request.url)
    const nickname = searchParams.get('nickname')
    const response = { nickname: nickname }
    await delay(500)
    return HttpResponse.json(response, { status: 200 })
  }),
  // 유저 정보 전달
  http.post(API_URI.BASE + API_URI.USER_INFO, async () => {
    await delay(1000)
    const response = {}
    return HttpResponse.json(response, { status: 200 })
  }),
  // 좌표 전달 후 장소 정보 반환 API
  http.post(API_URI.BASE + API_URI.PLACE_INFO, async () => {
    await delay(300)
    const response = {
      placeDtoList: [
        {
          id: 1,
          name: '역삼',
          address: '서울시 역삼동 255',
          url: 'www.naver.com',
          placeCategory: '맛집',
          placeType: '식당',
          points: { lat: 37.5, lng: 127.031 },
        },
        {
          id: 2,
          name: '역삼',
          address: '서울시 역삼동 255',
          url: 'www.naver.com',
          placeCategory: '맛집',
          placeType: '식당',
          points: { lat: 37.501, lng: 127.032 },
        },
        {
          id: 3,
          name: '역삼',
          address: '서울시 역삼동 255',
          url: 'www.naver.com',
          placeCategory: '맛집',
          placeType: '식당',
          points: { lat: 37.502, lng: 127.033 },
        },
        {
          id: 4,
          name: '역삼',
          address: '서울시 역삼동 255',
          url: 'www.naver.com',
          placeCategory: '맛집',
          placeType: '식당',
          points: { lat: 37.523, lng: 127.034 },
        },
      ],
    }
    return HttpResponse.json(response, { status: 200 })
  }),
]
