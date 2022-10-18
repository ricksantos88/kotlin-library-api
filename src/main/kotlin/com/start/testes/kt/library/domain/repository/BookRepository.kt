package com.start.testes.kt.library.domain.repository

import com.start.testes.kt.library.domain.model.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository: JpaRepository<Book, Long> {

    fun existsByIsbn(isbn: String): Boolean
}