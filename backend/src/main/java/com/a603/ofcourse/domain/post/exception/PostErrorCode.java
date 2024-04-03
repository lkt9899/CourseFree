package com.a603.ofcourse.domain.post.exception;

import com.a603.ofcourse.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PostErrorCode implements ErrorCode {
    POST_NOT_EXIST(403, "POST_01", "존재하지 않는 게시글입니다."),
    ;


    private final int statusCode;
    private final String errorCode;
    private final String message;
}
