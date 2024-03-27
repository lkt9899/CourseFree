import { useEffect, useState } from 'react'

import { requestNickNameValidCheck } from '@api/request/member'

/**
 * @summary : debouncedName을 입력받아 get요청을 통해 중복검사를 하고, 중복 여부를 state로 관리하는 훅입니다.
 * @param debouncedName : 중복 검사를 시행할 이름입니다.
 * @returns
 */
export default function useNickNameValidCheck(debouncedName: string) {
  const [isValidNickName, setIsValidNickName] = useState(false)

  useEffect(() => {
    async function nicknameValidCheck(name: string) {
      if (name === '') return
      try {
        const { status } = await requestNickNameValidCheck(name)
        if (status === 200) setIsValidNickName(true)
      } catch (error) {
        setIsValidNickName(false)
      }
    }
    nicknameValidCheck(debouncedName)
  }, [debouncedName])

  return [isValidNickName]
}
