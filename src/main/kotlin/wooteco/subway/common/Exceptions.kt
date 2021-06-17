package wooteco.subway.common

import org.springframework.http.HttpStatus

open class BusinessException(val httpStatus: HttpStatus, val errorMessage: ErrorMessage) :
    RuntimeException() {
    constructor(httpStatus: HttpStatus, message: String) : this(httpStatus, ErrorMessage(message))
}

open class BadRequestException(message: String) : BusinessException(HttpStatus.BAD_REQUEST, message)

class AuthorizationException(message: String) : BusinessException(HttpStatus.UNAUTHORIZED, message)

data class ErrorMessage(val message: String)