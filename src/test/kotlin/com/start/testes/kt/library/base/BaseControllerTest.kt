package com.start.testes.kt.library.base

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension

@Extensions(ExtendWith(SpringExtension::class))
@ActiveProfiles("test")
@AutoConfigureWebMvc
abstract class BaseControllerTest(
) {

}