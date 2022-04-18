package com.example.helloworld.common.response

import org.springframework.http.HttpStatus

/**
 * 공통 응답
 */
class CommonResponse<T>(
    /**
     * 응답 코드
     */
    val code: Int,

    /**
     * 응답 메세지
     */
    val message: String,

    /**
     * 응답 데이터
     */
    val content: T?
) {
    constructor(content: T): this(HttpStatus.OK.value(), HttpStatus.OK.reasonPhrase, content)

    constructor(httpStatus: HttpStatus, message: String? = null): this(httpStatus.value(), message ?: httpStatus.reasonPhrase, null)

    constructor(httpStatus: HttpStatus, content: T) : this(httpStatus.value(), httpStatus.reasonPhrase, content)
}