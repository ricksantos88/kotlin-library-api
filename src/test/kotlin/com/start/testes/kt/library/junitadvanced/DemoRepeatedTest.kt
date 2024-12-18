package com.start.testes.kt.library.junitadvanced

import com.start.testes.kt.library.math.SimpleMath
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.RepetitionInfo
import org.junit.jupiter.api.TestInfo
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

@Order(5)
class DemoRepeatedTest {

    private lateinit var math: SimpleMath

    @BeforeEach
    fun setupAll() {
        math = SimpleMath()
        println("Starting tests...")
    }

    @RepeatedTest(5)
    fun `test division when first number is divided by zero should throw arithmetic exception`(
        repetitionInfo: RepetitionInfo, testInfo: TestInfo
    ) {
        println("Running test ${repetitionInfo.currentRepetition}")

        // given
        val firstNumber = 5.2
        val secondNumber = 0.0

        val expectedMessage = "Division by zero is not allowed"

        //when and then
        val actual = assertThrows<ArithmeticException> {
            math.division(firstNumber, secondNumber)
        }
        assertEquals(expectedMessage, actual.message, "Expected exception message did not match")

    }
}