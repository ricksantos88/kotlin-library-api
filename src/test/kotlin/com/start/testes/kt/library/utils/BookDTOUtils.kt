package com.start.testes.kt.library.utils

import com.start.testes.kt.library.api.model.BookDTO

class BookDTOUtils {

    fun createNewBookDTO(): BookDTO {
        return BookDTO(id = 1L, title = "Meu Livro", author = "author", isbn = "123")
    }

    fun createNewBookDTOWithId(): BookDTO {
        return BookDTO(title = "Meu Livro", author = "author", isbn = "123", id = 1L)
    }

}