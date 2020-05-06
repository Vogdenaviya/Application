package com.example.jdbc

import com.example.unmarsh.Users

interface UsersDbEntityDAO {
    fun update(users: Users)
    fun findAllUsers(): MutableList<UsersDbEntity>?
    fun findNullStatus(): MutableList<UsersDbEntity>
    fun updateStatus(users: UsersDbEntity)
}