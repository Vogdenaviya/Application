package com.example.controller

import com.example.jdbc.UsersDbEntity
import com.example.jdbc.UsersDbEntityDAO
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UsersController(
        val userDbEntityDao: UsersDbEntityDAO
) {
    @GetMapping(value = ["/users"])
    fun getUsers(model: ModelMap): MutableList<UsersDbEntity>{
        val usersList: MutableList<UsersDbEntity>? = userDbEntityDao.findAllUsers()
        return usersList!!
    }
}