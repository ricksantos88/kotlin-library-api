package com.start.testes.kt.library.api.controllers

import com.start.testes.kt.library.api.model.BookDTO
import com.start.testes.kt.library.domain.model.Book
import com.start.testes.kt.library.domain.service.IBookService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid


@RestController
@RequestMapping("/api/books")
class BookController (val service: IBookService) {

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): BookDTO {
        return service
            .findById(id)
            .map(::converterToDTO)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody bookDTO: BookDTO): BookDTO {

        val book: Book = converterToEntity(bookDTO)
        return converterToDTO(service.save(book))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {

        val book: Book = service
            .findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
        service.delete(book)
    }

    fun converterToDTO(book: Book): BookDTO {
        return BookDTO(id = book.id, title = book.title, author = book.author, isbn = book.isbn)
    }

    fun converterToEntity(bookDTO: BookDTO): Book {
        return Book(id = bookDTO.id, title = bookDTO.title, author = bookDTO.author, isbn = bookDTO.isbn)
    }


}