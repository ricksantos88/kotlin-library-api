package com.start.testes.kt.library.utils

import com.start.testes.kt.library.domain.model.Book

class BookUtils {

    fun createNewBook(): Book
    {
        return Book(title = "Meu Livro", author = "author", isbn = "123")
    }

    fun createNewBookDTOWithId(): Book {
        return Book( id = 1L, title = "Meu Livro", author = "author", isbn = "123")
    }

}