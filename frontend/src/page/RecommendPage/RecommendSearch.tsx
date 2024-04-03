import { Badge, Stack } from '@chakra-ui/react'
import { Categories, category, categoryKey } from '@data/category'
import { Station } from '@type/subway'
import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { useSetRecoilState } from 'recoil'

import FullPageLoading from '@component/common/FullPageLoading'
import SearchAutoComplete from '@component/common/SearchAutoComplete'
import SearchBar from '@component/common/SearchBar'
import TextBox from '@component/common/TextBox'
import FlexBox from '@component/layout/FlexBox'
import Spacing from '@component/layout/Spacing'

import { useStationAutoCompleteQuery } from '@hook/ReactQuery/useStationAutoCompleteQuery'
import useInput from '@hook/useInput'

import { requestSubmitRecommData } from '@api/request/course'

import { recommendedCourse } from '@recoil/recommendedCourseAtom'

export default function RecommendSearch() {
  const setRecommendedCourseId = useSetRecoilState(recommendedCourse)

  const [isLoading, setIsLoading] = useState(false)
  const navigate = useNavigate()
  const { state: searchText, debounceChange: onChange } = useInput<string>({
    data: '',
    setDebounce: true,
  })

  const { stationList } = useStationAutoCompleteQuery(searchText)
  const [selectedCategory, setSelectedCategory] = useState<Categories[]>([])

  const addCategory = select => {
    if (selectedCategory.includes(select)) {
      setSelectedCategory(selectedCategory.filter(elem => elem != select))
    } else {
      setSelectedCategory([...selectedCategory, select])
    }
  }
  const submitRecommData = async (data: Station) => {
    // post 요청, loading상태 전환, promise.then에서 navigate, 코스 전역 상태 저장

    setIsLoading(true)
    const requestData = {
      points: {
        lat: data.point.lat,
        lng: data.point.lng,
      },
      limitDist: 1000,
      categoryList: selectedCategory,
    }
    await requestSubmitRecommData(requestData)
      .then(({ data }) => {
        // recoil로 코스 아이디 상태 저장
        setRecommendedCourseId(data)
        setIsLoading(false)
        navigate('./candidate')
      })
      .catch(error => {
        alert(error)
      })
  }

  if (isLoading) return <FullPageLoading />
  return (
    <>
      <FlexBox p="10px" d="column">
        <TextBox fontWeight="bold" typography="t4">
          3개 이상의 카테고리를 골라주세요.
        </TextBox>
        <Spacing />
        <Stack direction="row">
          {category.map((e: Categories) => {
            return (
              <Badge
                key={e}
                ml="1"
                fontSize="12px"
                onClick={() => {
                  addCategory(e)
                }}
                colorScheme={selectedCategory.includes(e) ? 'pink' : 'gray'}
              >
                {categoryKey[e]}
              </Badge>
            )
          })}
        </Stack>
        <SearchBar
          placeholder="지하철 역을 입력해주세요."
          onChange={onChange}
        />
        {stationList && (
          <SearchAutoComplete
            stationList={stationList.data}
            type="recommend"
            onClick={submitRecommData}
          />
        )}
      </FlexBox>
    </>
  )
}
