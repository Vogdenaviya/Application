package com.example.demo

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class Server {

    @RabbitListener(queues = ["myQueue"])
    fun getMessage(message: String): Int{
        println("GET MESSAGE")
        return (Math.random() * 100).toInt()
    }
}