import { requestGenerateLink } from '@api/request/member';
import { useEffect } from 'react';

function GenerateLink() {

    useEffect(()=>{
        // axios instance를 활용해서 api 호출
        requestGenerateLink().then((response)=>{
            // 요청 성공 시 해야할 일 하기
            console.log(response.data);
        }).catch((e)=>{
            console.error('API 호출 에러:', e);
        })
        
    },[])
    return(
        <>
            gd
        </>
    );
}

export default GenerateLink;
