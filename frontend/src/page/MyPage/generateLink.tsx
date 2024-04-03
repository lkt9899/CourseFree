import { requestGenerateLink,requestSendLink } from '@api/request/member';
import { useEffect, useState } from 'react';
// import { authAxios } from '@api/index'

function GenerateLink() {
    const [generatedLink, setGeneratedLink] = useState('');
    const [loading, setLoading] = useState(true); // 링크 생성 상태를 나타내는 loading 상태 추가

    useEffect(() => {
        // axios instance를 활용해서 api 호출
        requestGenerateLink()
            .then((response) => {
                // 요청 성공 시, 서버에서 받은 링크를 상태에 저장합니다.
                console.log(response.data);
                setGeneratedLink(response.data);
                setLoading(false); // 로딩 상태 업데이트
            })
            .catch((e) => {
                console.error('API 호출 에러:', e);
                setLoading(false); // 에러 발생 시에도 로딩 상태 업데이트
            });
    }, []);

    // 링크 생성 상태에 따라 적절한 내용을 표시합니다.
    return (
        <>
            {loading ? (
                <p>링크 생성 중...</p>
            ) : (
                <>
                    <p>생성된 링크: {generatedLink}</p>
                    {/* 생성된 링크가 있을 때만 POST 요청 버튼을 보여줍니다. */}
                    {generatedLink && (
    <button onClick={() => requestSendLink(generatedLink)}>링크로 POST 요청 보내기</button>
)}
                </>
            )}
        </>
    );

}

export default GenerateLink;
