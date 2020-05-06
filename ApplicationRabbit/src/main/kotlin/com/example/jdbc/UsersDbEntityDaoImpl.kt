package com.example.jdbc

import com.example.unmarsh.Users
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.jdbc.support.KeyHolder
import org.springframework.stereotype.Repository

//Работа с БД

const val INSERT_USERS: String = "INSERT INTO users(name, birthday) VALUES(?,?)"                        //Заполнить таблицу users
const val INSERT_HOBBIES: String = "INSERT INTO hobbies(hobby, hours, users_id) VALUES(?,?,?)"          //Заполнить таблицу hobbies
const val SQL_FROM_USERS_DATABASE: String = "SELECT users.id, name, birthday, hobby, hours, status FROM " +
        "users INNER JOIN hobbies ON users.id = hobbies.users_id"                                       //Выбрать содержиое БД
const val SQL_SELECT_NULL_STATUS = "SELECT id, status FROM users WHERE status IS NULL"                //Выбрать значение с null status
const val SQL_UPDATE_STATUS = "UPDATE users SET status = ? WHERE id = ?"                              //Обновить status

@Repository
class UsersDbEntityDaoImpl(
        val jdbcTemplate: JdbcTemplate
): UsersDbEntityDAO {

    override fun update(users: Users) {
        val holder: KeyHolder = GeneratedKeyHolder()                             //Генерация ключа
        jdbcTemplate.update(UsersInsert(users), holder)                          //Заполнить таблицу users
        jdbcTemplate.update(HobbiesInsert(users, holder.key!!.toInt()))          //Заполнить таблицу hobbies
    }

    override fun findAllUsers(): MutableList<UsersDbEntity>? {                       //Получить содержимое БД
        return jdbcTemplate.query(SQL_FROM_USERS_DATABASE, UsersRowMapper())
    }

    override fun findNullStatus(): MutableList<UsersDbEntity> {                 //Получить строки с null status
        return jdbcTemplate.query(SQL_SELECT_NULL_STATUS, UsersRowMapperForNullStatus())
    }

    override fun updateStatus(users: UsersDbEntity) {                               //Обновить status
        jdbcTemplate.update(SQL_UPDATE_STATUS,users.status,users.id)
    }
}


