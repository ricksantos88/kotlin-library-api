package com.start.testes.kt.library.domain.service.impl

import com.start.testes.kt.library.domain.model.Book
import com.start.testes.kt.library.domain.repository.BookRepository
import com.start.testes.kt.library.domain.service.IBookService
import com.start.testes.kt.library.exceptions.BusinessException
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.awt.print.Pageable
import java.util.*

@Service
class BookService(val repository: BookRepository): IBookService {
    override fun save(book: Book): Book {
        if (book.isbn?.let { repository.existsByIsbn(it) } == true) {
            throw BusinessException("ISBN já cadastrado.")
        }
        return repository.save(book)
    }

    override fun findById(id: Long): Optional<Book> {
        return repository.findById(id)
    }

    override fun delete(book: Book) {
        if (book.id == null) {
            throw IllegalArgumentException("Book id cant no be null")
        }
        repository.delete(book)
    }

    override fun update(book: Book): Book {
        TODO("Not yet implemented")
    }

    override fun find(filter: Book, pageRequest: Pageable): Page<Book> {
        TODO("Not yet implemented")
    }

    override fun getBookByIsbn(isbn: String): Optional<Book> {
        TODO("Not yet implemented")
    }
}