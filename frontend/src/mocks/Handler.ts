import { HttpResponse, delay, http } from 'msw'

import API_URI from '@api/url'

export const handlers = [
  // 이메일 중복 체크 모킹
  http.get(API_URI.BASE + API_URI.VALID_CHECK, async ({ request }) => {
    const { searchParams } = new URL(request.url)
    const nickname = searchParams.get('nickname')
    const response = { nickname: nickname }
    await delay(500)
    return HttpResponse.json(response, { status: 200 })
  }),
  http.post(API_URI.BASE + API_URI.USER_INFO, async () => {
    await delay(1000)
    const response = {}
    return HttpResponse.json(response, { status: 200 })
  }),
]
