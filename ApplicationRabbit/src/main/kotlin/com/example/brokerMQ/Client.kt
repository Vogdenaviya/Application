package com.example.brokerMQ

import com.example.jdbc.UsersDbEntity
import com.example.jdbc.UsersDbEntityDAO
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class Client {

    @Autowired
    val rabbitTemplate: RabbitTemplate? = null

    @Autowired
    val usersDbEntityDAO: UsersDbEntityDAO? = null
    var usersList: MutableList<UsersDbEntity>? = null

    @Scheduled(fixedDelay = 4000)
    fun call(){
        println("Запрос")
        usersList = usersDbEntityDAO?.findNullStatus()
        usersList?.iterator()?.forEach { println(it) }

        for(user in usersList!!){
            val newMessage = rabbitTemplate?.convertSendAndReceive(
                    "myQueue", "message")
            user.status = newMessage as Int?
            usersDbEntityDAO?.updateStatus(user)
        }
    }
}