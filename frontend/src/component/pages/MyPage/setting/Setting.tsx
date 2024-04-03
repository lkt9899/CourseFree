// 추가: modifyCoupleProfile, modifyProfile 함수 import
import { faEdit, faUserCircle } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { useEffect, useState } from 'react'

import {
  requestModifyCoupleProfile,
  requestModifyProfile,
  requestProfile,
} from '@api/request/member'

import {
  Age,
  BottomContainer,
  Container,
  Couple,
  EditButton,
  FormContainer,
  Icon,
  InfoContainer,
  MainContainer,
  Name,
  NickName,
  SaveButton,
  Title,
} from '@styled/component/pages/MyPage/Setting/Setting'

// 추가: useState import

const Setting = () => {
  const [memberNickname, setMemberNickname] = useState('')
  const [partnerNickname, setPartnerNickname] = useState('')
  const [memberImage, setMemberImage] = useState<string | null>(null)
  const [isCouple, setIsCouple] = useState(false) // 기본값 false로 설정
  const [gender, setGender] = useState('')
  const [coupleNickname, setCoupleNickname] = useState('')
  const [isEditing, setIsEditing] = useState(false) // 수정 상태 여부
  useEffect(() => {
    requestProfile()
      .then(response => {
        console.log(response.data)
        setMemberNickname(response.data.nickname)
        setPartnerNickname(response.data.partnerNickname)
        if (typeof response.data.image === 'string') {
          setMemberImage(response.data.image)
        }
        setGender(response.data.gender)
        setIsCouple(response.data.isCouple)
        setCoupleNickname(response.data.coupleNickname)
      })
      .catch(error => {
        console.error('API 호출 에러:', error)
      })
  })

  const handleEditToggle = () => {
    setIsEditing(!isEditing)
  }

  const map = { 남성: 'MALE', 여성: 'FEMALE' }
  // 프로필 수정 함수
  const handleProfileUpdate = () => {
    const requestData = {
      nickname: memberNickname,
      image: memberImage,
      gender: map[gender],
    }

    const requestCoupleData = {
      nickname: memberNickname,
      image: memberImage,
      gender: map[gender],
      coupleNickname: coupleNickname,
    }

    // isCouple 값에 따라 다른 API 호출
    if (isCouple) {
      // 커플일 때의 프로필 수정 API 호출
      requestModifyCoupleProfile(requestCoupleData)
        .then(response => {
          console.log('커플 프로필 수정 성공:', response)
          // 수정 성공 시 필요한 작업 수행
        })
        .catch(error => {
          console.error('커플 프로필 수정 에러:', error)
        })
    } else {
      // 커플이 아닐 때의 프로필 수정 API 호출
      requestModifyProfile(requestData)
        .then(response => {
          console.log('개인 프로필 수정 성공:', response)
          // 수정 성공 시 필요한 작업 수행
        })
        .catch(error => {
          console.error('개인 프로필 수정 에러:', error)
        })
    }
  }

  return (
    <Container>
      <Title>
        내 정보
        <hr />
      </Title>
      <InfoContainer>
        <Icon>
          {memberImage ? (
            <img src={memberImage} alt="Member Image" />
          ) : (
            <FontAwesomeIcon icon={faUserCircle} size="5x" />
          )}
          {/* 수정 버튼 추가 */}
          <EditButton onClick={handleEditToggle}>
            <FontAwesomeIcon icon={faEdit} />
          </EditButton>
        </Icon>
        <Name>
          <p>{memberNickname}</p>
        </Name>
        <NickName></NickName>
        <Age>
          <p>{gender}</p>
        </Age>
        <Couple>
          {partnerNickname && <p>{partnerNickname}님과 커플이예요</p>}
        </Couple>
      </InfoContainer>
      {/* 수정 폼 */}
      {isEditing && (
        <FormContainer>
          {/* 폼에 프로필 수정에 필요한 입력 요소들을 추가 */}
          <input
            type="text"
            value={memberNickname}
            onChange={e => setMemberNickname(e.target.value)}
          />
          {/* 나머지 정보에 대해서도 위와 같은 형태로 추가 */}
          <SaveButton onClick={handleProfileUpdate}>저장</SaveButton>
        </FormContainer>
      )}
      <MainContainer>
        <p></p>
        <p></p>
        <p>공지 사항</p>
        <p>서비스 정보 및 약관</p>
        <p></p>
        <p></p>
      </MainContainer>
      <BottomContainer>
        {/* 회원 탈퇴와 커플 해제 버튼도 필요하다면 추가 */}
        <p>회원 탈퇴</p>
        <p>커플 해제</p>
      </BottomContainer>
    </Container>
  )
}

export default Setting
