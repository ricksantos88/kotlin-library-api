package com.start.testes.kt.library.junitadvanced

import com.start.testes.kt.library.math.SimpleMath
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Order
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.*
import java.util.stream.Stream
import kotlin.test.assertNotNull

@Order(4)
class SimpleMathTestJunitAdvanced {

    private lateinit var math: SimpleMath

    @BeforeEach
    fun setupAll() {
        math = SimpleMath()
        println("Starting tests...")
    }

    @ParameterizedTest
    @MethodSource("provideNumbers")
    fun `test division using method source`(firstNumber: Double, secondNumber: Double, expected: Double){
        val result: Double = math.division(firstNumber, secondNumber)
        assertEquals(expected, result, 2.toDouble(),"The division of $firstNumber and $secondNumber should be $expected")
    }

    @ParameterizedTest
    @CsvSource(
        "5.2, 2.0, 2.6",
        "10.4, 2.0, 5.2",
        "15.6, 2.0, 7.8"
    )
    fun `test division using csv source`(firstNumber: Double, secondNumber: Double, expected: Double){
        val result: Double = math.division(firstNumber, secondNumber)
        assertEquals(expected, result, 2.toDouble(),"The division of $firstNumber and $secondNumber should be $expected")
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/files/testDivision.csv"])
    fun `test division using csv file`(firstNumber: Double, secondNumber: Double, expected: Double){
        val result: Double = math.division(firstNumber, secondNumber)
        assertEquals(expected, result, 2.toDouble(),"The division of $firstNumber and $secondNumber should be $expected")
    }

    @ParameterizedTest
    @ValueSource(strings = ["Pelé", "Senna", "Marta"])
    fun `test value source`(firstName: String){
        println("First name: $firstName")
        assertNotNull(firstName)
    }

    companion object {
        @JvmStatic
        private fun provideNumbers(): Stream<Arguments> = Stream.of(
            Arguments.of(5.2, 2.0, 2.6),
            Arguments.of(10.4, 2.0, 5.2),
            Arguments.of(15.6, 2.0, 7.8)
        )
    }

}