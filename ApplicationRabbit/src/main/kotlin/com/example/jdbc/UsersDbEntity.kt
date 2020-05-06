package com.example.jdbc

import java.util.*

class UsersDbEntity(
        var id: Int? = null,
        var name: String? = null,
        var birthday: Date? = null,
        var hobby: String? = null,
        var hours: Int? = null,
        var status: Int? = null
) {
    override fun toString(): String {
        return "UsersDbEntity(id=$id, name=$name, birthday=$birthday, hobby=$hobby, hours=$hours, status=$status)"
    }
}