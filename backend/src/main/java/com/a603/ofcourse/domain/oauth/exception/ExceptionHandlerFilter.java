package com.a603.ofcourse.domain.oauth.exception;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
/*
JwtFilter 내부에서 CustomException 사용이 가능하도록 하기 위한 Filter
 */
public class ExceptionHandlerFilter extends OncePerRequestFilter {
    /*
    작성자 : 김은비
    작성일자 : 2024-03-13
    작성내용 : HTTP 요청을 필터링하고 처리하며 예외가 발생하면 처리
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
            try{
                //1. fiterChain 객체를 사용하여 다음 필터로 요청을 전달
                filterChain.doFilter(request, response);
            }catch(OauthException e){
                setErrorResponse(e.getStatusCode(), response, e);
            }catch(Exception e){
                setErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), response, e);
            }
    }

    /*
    작성자 : 김은비
    작성일자 : 2024-03-13
    작성내용 : 예외가 발생했을 때 클라이언트에게 적절한 에러 응답을 생성하여 전송
     */
    public void setErrorResponse(int status, HttpServletResponse response, Throwable e) throws IOException{
        //1. 예외 발생했음 명시
        logger.error("[ExceptionHandlerFilter] errMsg : " + e.getMessage());

        //2. 응답의 상태 코드 설정
        response.setStatus(status);
        //3. 응답의 콘텐츠 타입을 설정 -> JSON 형식, 인코딩 설정
        response.setContentType("application/json; charset=UTF-8");

        //4. 예외메세지로 ErrorResponse객체 생성하여 JSON 형식으로 변환 후 응답에 기록
        response.getWriter().write(
                new ErrorResponse(e.getMessage())
                        .convertToJson()
        );
    }
}
