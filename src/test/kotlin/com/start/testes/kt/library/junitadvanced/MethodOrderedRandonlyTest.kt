package com.start.testes.kt.library.junitadvanced

import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder

@Order(1) // This annotation is to define order to class
@TestMethodOrder(MethodOrderer.Random::class)
//@TestMethodOrder(MethodOrderer.MethodName::class)
//@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class MethodOrderedRandomlyTest {

    @Test
//    @Order(1)
    fun `test a` () {
        println("Running test a")
    }

    @Test
//    @Order(2)
    fun `test b` () {
        println("Running test b")
    }

    @Test
//    @Order(3)
    fun `test c` () {
        println("Running test c")
    }

    @Test
//    @Order(4)
    fun `test d` () {
        println("Running test d")
    }

}