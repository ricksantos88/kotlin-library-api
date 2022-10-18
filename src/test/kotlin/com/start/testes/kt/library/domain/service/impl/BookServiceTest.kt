package com.start.testes.kt.library.domain.service.impl

import com.start.testes.kt.library.domain.model.Book
import com.start.testes.kt.library.domain.repository.BookRepository
import com.start.testes.kt.library.domain.service.IBookService
import com.start.testes.kt.library.exceptions.BusinessException
import com.start.testes.kt.library.utils.BookUtils
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*


@Extensions(ExtendWith(SpringExtension::class))
@ActiveProfiles("test")
internal class BookServiceTest() {

    lateinit var service: IBookService

    @MockBean
    lateinit var  repository: BookRepository

    private var bookUtils = BookUtils()

    @BeforeEach
    fun setUp() {
        service = BookService(repository)
    }

    @Test
    fun `should save a book`() {
        val book: Book = Book(title = "As Crônicas de Nárnia", author = "C.S. Lewis", isbn = "112233")
        val bookToSave: Book = Book(id = 1L, title = "As Crônicas de Nárnia", author = "C.S. Lewis", isbn = "112233")

        `when`(book.isbn?.let { repository.existsByIsbn(it) }).thenReturn(false)
        `when`(repository.save(book)).thenReturn(bookToSave)

        val bookSaved: Book = service.save(book)

        assertThat(bookSaved.id).isNotNull
        assertThat(bookSaved.isbn).isEqualTo("112233");
        assertThat(bookSaved.title).isEqualTo("As Crônicas de Nárnia");
        assertThat(bookSaved.author).isEqualTo("C.S. Lewis");
    }

    @Test
    fun `should throw a business error when trying to create a book with duplicate isbn`() {
        val book = Book(title = "As Crônicas de Nárnia", author = "C.S. Lewis", isbn = "112233")
        val bookToSave = Book(id = 1L, title = "As Crônicas de Nárnia", author = "C.S. Lewis", isbn = "112233")

        `when`(book.isbn?.let { repository.existsByIsbn(it) }).thenReturn(true)
        `when`(repository.save(book)).thenReturn(bookToSave)

        val ex: Throwable = Assertions.catchThrowable { service.save(book) }

        val message = "ISBN já cadastrado."
        assertThat(ex)
            .isInstanceOf(BusinessException::class.java)
            .hasMessage(message)
    }

    @Test
    fun `should get a book by id`() {
        val id = 1L
        val book = bookUtils.createNewBookDTOWithId()
        book.id = id

        `when`(repository.findById(id)).thenReturn(Optional.of(book))

        val foundBook = service.findById(id);

        assertThat(foundBook.isPresent).isTrue
        assertThat(foundBook.get().id).isEqualTo(id);
        assertThat(foundBook.get().author).isEqualTo(book.author);
        assertThat(foundBook.get().title).isEqualTo(book.title);
        assertThat(foundBook.get().isbn).isEqualTo(book.isbn);

    }

    @Test
    fun `should return empty when trying to get a book that doesn't exist in the database`() {
        val id = 1L

        `when`(repository.findById(id)).thenReturn(Optional.empty())

        val bookNotFound = service.findById(id)

        assertThat(bookNotFound.isPresent).isFalse
    }

    @Test
    fun `should return empty when delete a book`() {
        val book = bookUtils.createNewBookDTOWithId()
        assertDoesNotThrow { service.delete(book) }

        verify(repository, Mockito.times(1)).delete(book)
    }

    @Test
    fun `should error occur when trying to delete nonexistent book`() {
        val book = Book(title = null, author = null, isbn = null)

        assertThrows(IllegalArgumentException::class.java) { service.delete(book) }
        verify(repository, Mockito.never()).delete(book)
    }
}