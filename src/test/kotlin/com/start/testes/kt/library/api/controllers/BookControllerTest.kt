package com.start.testes.kt.library.api.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.start.testes.kt.library.api.model.BookDTO
import com.start.testes.kt.library.domain.model.Book
import com.start.testes.kt.library.domain.service.IBookService
import com.start.testes.kt.library.exceptions.BusinessException
import com.start.testes.kt.library.utils.BookDTOUtils
import com.start.testes.kt.library.utils.BookUtils
import org.hamcrest.Matchers.hasSize
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions
import org.mockito.BDDMockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*


@Extensions(ExtendWith(SpringExtension::class))
@ActiveProfiles("test")
@WebMvcTest(controllers = [BookController::class])
@AutoConfigureWebMvc
internal class BookControllerTest @Autowired constructor(private val mockMvc: MockMvc) {

    private val BOOK_API: String = "/api/books"

    @MockBean
    lateinit var service: IBookService

    private var dtoUtils = BookDTOUtils()
    private var bookUtils = BookUtils()

    @Test
    fun `should create book with a success`() {
        //Cenário
        val bookDto: BookDTO = dtoUtils.createNewBookDTO();
        val bookConverted = Book(id = 1L, title = "Meu Livro", author = "author", isbn = "123")
        val bookSaved = Book(id = 1L, title = "Meu Livro", author = "author", isbn = "123")

        //Execução
        given(service.save(bookConverted)).willReturn(bookSaved)
        val json: String = ObjectMapper().writeValueAsString(bookDto)

        val request: MockHttpServletRequestBuilder = MockMvcRequestBuilders
            .post(BOOK_API)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .content(json)

        //Verificação
        mockMvc.perform(request)
            .andExpect(status().isCreated)
            .andExpect(jsonPath("id").isNotEmpty)
            .andExpect(jsonPath("title").value(bookDto.title))
            .andExpect(jsonPath("author").value(bookDto.author))
            .andExpect(jsonPath("isbn").value(bookDto.isbn));
    }

    @Test
    fun `should throw an error when the fields provided are not enough to create the book`() {

        val json: String = ObjectMapper().writeValueAsString(BookDTO())

        val request: MockHttpServletRequestBuilder = MockMvcRequestBuilders
            .post(BOOK_API)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .content(json)

        mockMvc
            .perform(request)
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("errors", hasSize<Int>(3)))
    }

    @Test
    fun `should throw an error when trying to create a book with duplicate isbn`() {

        val dto = dtoUtils.createNewBookDTOWithId()
        val json: String = ObjectMapper().writeValueAsString(dto)
        val message = "ISBN já cadastrado."
        val bookToSaved = Book(id = 1L, title = "Meu Livro", author = "author", isbn = "123")

        given(service.save(bookToSaved)).willThrow(BusinessException(message))

        val request: MockHttpServletRequestBuilder = MockMvcRequestBuilders
            .post(BOOK_API)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .content(json)

        mockMvc
            .perform(request)
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("errors", hasSize<Int>(1)))
            .andExpect(jsonPath("errors[0]").value(message))

    }

    @Test
    fun `should get information a book`() {

        val id = 1L
        val book = bookUtils.createNewBook()
        book.id = id

        given(service.findById(id)).willReturn(Optional.of(book))

        val request: MockHttpServletRequestBuilder = MockMvcRequestBuilders
            .get("$BOOK_API/$id")
            .accept(APPLICATION_JSON)

        val bookGetted = dtoUtils.createNewBookDTOWithId()

        mockMvc.perform(request)
            .andExpect(status().isOk)
            .andExpect(jsonPath("id").value(id))
            .andExpect(jsonPath("title").value(bookGetted.title))
            .andExpect(jsonPath("author").value(bookGetted.author))
            .andExpect(jsonPath("isbn").value(bookGetted.isbn));

    }

    @Test
    fun `should return resource not found when book not exists`() {
        val id = 1L
        given(service.findById(id)).willReturn(Optional.empty())

        val request: MockHttpServletRequestBuilder = MockMvcRequestBuilders
            .get("$BOOK_API/$id")
            .accept(APPLICATION_JSON)

        mockMvc
            .perform(request)
            .andExpect(status().isNotFound)

    }

    @Test
    fun `should delete book`() {
        val id = 1L

        given(service.findById(anyLong())).willReturn(Optional.of(bookUtils.createNewBookDTOWithId()))
        val request: MockHttpServletRequestBuilder = MockMvcRequestBuilders
            .delete("$BOOK_API/$id")

        mockMvc
            .perform(request)
            .andExpect(status().isNoContent)

    }

    @Test
    fun `should return resource not found when the book to delete is not found`() {
        val id = 1L
        given(service.findById(id)).willReturn(Optional.empty())

        val request: MockHttpServletRequestBuilder = MockMvcRequestBuilders
            .delete("$BOOK_API/$id")

        mockMvc
            .perform(request)
            .andExpect(status().isNotFound)
    }

}