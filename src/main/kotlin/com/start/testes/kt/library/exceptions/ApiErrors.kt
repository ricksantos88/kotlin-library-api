package com.start.testes.kt.library.exceptions

import lombok.Data
import org.springframework.validation.FieldError
import org.springframework.web.server.ResponseStatusException

@Data
class ApiErrors {

    private var errors: MutableList<String> = ArrayList()

    constructor(fieldsErrors: List<FieldError>) {
        fieldsErrors.forEach {
            val defaultMessage = "${it.field} ${it.defaultMessage}"
            errors.add(defaultMessage)
        }
    }

    constructor(businessException: BusinessException) {
        businessException.message?.let { errors.add(it) }
    }

    constructor(responseStatusException: ResponseStatusException) {
        responseStatusException.reason?.let { errors.add(it) }
    }

    fun getErrors(): List<String?> {
        return errors
    }

}