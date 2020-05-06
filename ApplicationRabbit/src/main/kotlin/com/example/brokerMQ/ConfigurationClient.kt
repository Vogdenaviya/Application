package com.example.brokerMQ

import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ConfigurationClient {

    @Bean
    fun client() = Client()

    @Bean
    fun queue() = Queue("myQueue")

    @Bean
    fun exchange() = DirectExchange("direct")
}