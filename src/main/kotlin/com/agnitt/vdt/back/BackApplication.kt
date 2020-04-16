package com.agnitt.vdt.back

import com.agnitt.vdt.back.controllers.Controller
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@SpringBootApplication
class BackApplication

@Bean
fun controller() = Controller()

fun main(args: Array<String>) {
    runApplication<BackApplication>(*args)
}
