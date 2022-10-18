package com.start.testes.kt.library.api.advicers

import com.start.testes.kt.library.exceptions.ApiErrors
import com.start.testes.kt.library.exceptions.BusinessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.server.ResponseStatusException

@RestControllerAdvice
class ApplicationControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationException(argumentNotValidException: MethodArgumentNotValidException): ApiErrors? {
        val fieldsErrors = argumentNotValidException.fieldErrors
        return ApiErrors(fieldsErrors)
    }

    @ExceptionHandler(BusinessException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleBusinessException(exception: BusinessException): ApiErrors {
        return ApiErrors(exception)
    }

    @ExceptionHandler(ResponseStatusException::class)
    fun handleResponseStatusException(responseStatusException: ResponseStatusException): ResponseEntity<*>? {
        return ResponseEntity(ApiErrors(responseStatusException), responseStatusException.status)
    }

}