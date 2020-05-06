package com.example.jdbc

import com.example.unmarsh.Users
import org.springframework.jdbc.core.PreparedStatementCreator
import java.sql.Connection
import java.sql.PreparedStatement

//Заполнение таблицы hobbies

class HobbiesInsert(
        val users: Users,
        val holder: Int
): PreparedStatementCreator {

    override fun createPreparedStatement(con: Connection): PreparedStatement {
        var key = holder - users.usersList.size + 1
        val prepared = con.prepareStatement(INSERT_HOBBIES)
        for (userList in users.usersList) {
            for(hobbyList in userList.hobbies){
                prepared.setString(1,hobbyList.hobby)
                prepared.setInt(2, hobbyList.hours!!)
                prepared.setInt(3, key)
                if ((userList != users.usersList.last()) and (hobbyList != userList.hobbies.last())) {
                    prepared.execute()
                }
                else if((userList != users.usersList.last()) and (hobbyList == userList.hobbies.last())){
                    prepared.execute()
                }
                else if((userList == users.usersList.last()) and (hobbyList != userList.hobbies.last())){
                    prepared.execute()
                }
            }
            key++
        }
        return prepared
    }
}