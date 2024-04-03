package com.a603.ofcourse.global.s3.exception;

import com.a603.ofcourse.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum S3ErrorCode implements ErrorCode {
    IS_NOT_IMAGE(400, "AWS_S3_01", "지원하는 파일 형식이 아닙니다.(jpg, png 등 사진만 가능"),
    CAN_NOT_ACCESS_TO_FILE(403, "AWS_S3_02", "접근 권한이 없거나 임시 저장에 실패했습니다."),
    ;

    private final int statusCode;
    private final String errorCode;
    private final String message;
}
