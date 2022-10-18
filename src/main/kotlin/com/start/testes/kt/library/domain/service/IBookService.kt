package com.start.testes.kt.library.domain.service

import com.start.testes.kt.library.domain.model.Book
import org.springframework.data.domain.Page
import java.awt.print.Pageable
import java.util.*

interface IBookService {

    fun save(book: Book): Book

    fun findById(id: Long): Optional<Book>

    fun delete(book: Book)

    fun update(book: Book): Book

    fun find(filter: Book, pageRequest: Pageable): Page<Book>

    fun getBookByIsbn(isbn: String): Optional<Book>

}