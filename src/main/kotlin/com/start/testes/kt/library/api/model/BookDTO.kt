package com.start.testes.kt.library.api.model

import javax.validation.constraints.NotBlank

data class BookDTO constructor(
    val id: Long? = null,
    @field: NotBlank val title: String? = null,
    @field: NotBlank val author: String? = null,
    @field: NotBlank val isbn: String? = null
) {
}
