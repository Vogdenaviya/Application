package com.example.jdbc

import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class UsersRowMapper: RowMapper<UsersDbEntity> {
    override fun mapRow(rs: ResultSet, rowNum: Int): UsersDbEntity? {
        val users = UsersDbEntity()
        users.id = rs.getInt("id")
        users.name = rs.getString("name")
        users.birthday = rs.getDate("birthday")
        users.hobby = rs.getString("hobby")
        users.hours = rs.getInt("hours")
        users.status = rs.getInt("status")
        return users
    }
}