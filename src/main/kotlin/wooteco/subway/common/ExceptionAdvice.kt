package wooteco.subway.common

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionAdvice(
    var logger: Logger = LoggerFactory.getLogger(
        ExceptionAdvice::class.java
    )
) {
    @ExceptionHandler(BusinessException::class)
    fun handleRuntimeException(e: BusinessException): ResponseEntity<ErrorMessage> {
        logger.error(e.message, e)
        return ResponseEntity.status(e.httpStatus).body(e.errorMessage)
    }
}