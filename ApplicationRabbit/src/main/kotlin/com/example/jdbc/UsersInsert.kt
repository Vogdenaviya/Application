package com.example.jdbc

import com.example.unmarsh.Users
import org.springframework.jdbc.core.PreparedStatementCreator
import java.sql.Connection
import java.sql.Date
import java.sql.PreparedStatement
import java.sql.Statement

//Зполнение таблицы users

class UsersInsert(
        val users: Users
): PreparedStatementCreator {
    override fun createPreparedStatement(con: Connection): PreparedStatement {
        val prepared = con.prepareStatement(INSERT_USERS, Statement.RETURN_GENERATED_KEYS)
        for (userList in users.usersList) {
            prepared.setString(1, userList.name)
            prepared.setDate(2, userList.birthday?.time?.let { Date(it) })
            if (userList != users.usersList.last()) {
                prepared.execute()
            }
        }
        return prepared
    }
}

