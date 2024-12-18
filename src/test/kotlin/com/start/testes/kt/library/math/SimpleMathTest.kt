package com.start.testes.kt.library.math

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test

@Order(3)
class SimpleMathTest {

    private lateinit var math: SimpleMath

    @BeforeEach
    fun setupAll() {
        math = SimpleMath()
        println("Starting tests...")
    }

    @Test
    fun `test sum`(){
        val result: Double = math.sum(5.2, 2.0)
        assertEquals(7.2, result, "The sum of 5.2 and 2.0 should be 7.2")
    }

    @Test
    fun `test sum v2`(){
        val firstNumber = 6.2
        val secondNumber = 2.0

        val actual: Double = math.sum(firstNumber, secondNumber)
        val expected = 8.2

        assertEquals(expected, actual, "$firstNumber +  $secondNumber  should be $expected!")
        assertNotEquals(8.3, actual)
        assertNotNull(actual)
    }

    @Test
    fun `test subtraction`(){
        val result: Double = math.subtraction(5.2, 2.0)
        assertEquals(3.2, result, "The subtraction of 5.2 and 2.0 should be 3.2")
    }

    @Test
    fun `test multiplication`(){
        val result: Double = math.multiplication(5.2, 2.0)
        assertEquals(10.4, result, "The multiplication of 5.2 and 2.0 should be 10.4")
    }

    @Test
    fun `test division`(){
        val result: Double = math.division(5.2, 2.0)
        assertEquals(2.6, result, "The division of 5.2 and 2.0 should be 2.6")
    }

    @Test
    fun `test mean`(){
        val result: Double = math.mean(5.2, 2.0)
        assertEquals(3.6, result, "The mean of 5.2 and 2.0 should be 3.6")
    }

    @Test
    fun `test squareRoot`(){
        val result: Double = math.squareRoot(9.0)
        assertEquals(3.0, result, "The square root of 9.0 should be 3.0")
    }

}