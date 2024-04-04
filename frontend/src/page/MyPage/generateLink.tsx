import { useEffect, useState } from 'react';
import { authAxios } from '@api/index';

interface ApiResponse {
    data: string;
}

function GenerateLink() {
    const [generatedLink, setGeneratedLink] = useState<string>('');
    const [loading, setLoading] = useState<boolean>(true);
    const [copied, setCopied] = useState<boolean>(false);
    const [inputLink, setInputLink] = useState<string>('');

    useEffect(() => {
        requestGenerateLink()
            .then((response: ApiResponse) => {
                console.log(response.data);
                setGeneratedLink(response.data);
                setLoading(false);
            })
            .catch((e: Error) => {
                console.error('API 호출 에러:', e);
                setLoading(false);
            });
    }, []);

    function requestGenerateLink(): Promise<ApiResponse> {
        return new Promise((resolve) => {
            setTimeout(() => {
                resolve({ data: 'http://generated.link' });
            }, 2000);
        });
    }

    function copyLinkToClipboard() {
        navigator.clipboard.writeText(generatedLink)
            .then(() => {
                setCopied(true);
                setTimeout(() => {
                    setCopied(false);
                }, 3000);
            })
            .catch((error) => {
                console.error('링크 복사 에러:', error);
            });
    }

    function sendPostRequest() {
        authAxios.post(inputLink)
        .then((response) => {
                console.log(inputLink)
                console.log('POST 요청 성공:', response);
                alert('연동 성공!'); // POST 요청이 성공하면 '연동 성공!' 메시지를 띄웁니다.
                window.history.back(); // window 객체의 history를 사용하여 뒤로가기를 실행합니다.
            })
            .catch((error) => {
                console.error('POST 요청 에러:', error);
            });
    }

    return (
        <>
            {loading ? (
                <p>링크 생성 중...</p>
            ) : (
                <>
                    <p>생성된 링크: {generatedLink}</p>
                    <button onClick={copyLinkToClipboard}>{copied ? '링크 복사됨' : '링크 복사하기'}</button>
                    <input type="text" value={inputLink} onChange={(e) => setInputLink(e.target.value)} placeholder="연동하려는 상대의 링크를 붙여넣어주세요" />
                    <button onClick={sendPostRequest}>요청 보내기</button>
                </>
            )}
        </>
    );
}

export default GenerateLink;
