package com.start.testes.kt.library.math

import org.springframework.core.annotation.Order
import kotlin.math.sqrt

@Order(2)
class SimpleMath {

    fun sum(a: Double, b: Double): Double = a + b

    fun subtraction(a: Double, b: Double): Double = a - b

    fun multiplication(a: Double, b: Double): Double = a * b

    fun division(a: Double, b: Double): Double {
        if (b == 0.0) {
            throw ArithmeticException("Division by zero is not allowed")
        }
        return a / b
    }

    fun mean(a: Double, b: Double): Double = (a + b) / 2

    fun squareRoot(a: Double): Double = sqrt(a)

}