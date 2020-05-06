package com.example.jdbc

import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class UsersRowMapperForNullStatus: RowMapper<UsersDbEntity> {
    override fun mapRow(rs: ResultSet, rowNum: Int): UsersDbEntity? {
        val users = UsersDbEntity()
        users.id = rs.getInt("id")
        users.status = rs.getInt("status")
        return users
    }
}