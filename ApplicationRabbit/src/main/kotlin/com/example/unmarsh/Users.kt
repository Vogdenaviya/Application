package com.example.unmarsh

import java.util.*
import javax.xml.bind.annotation.*
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
class Users() {
    @field:XmlElement(name = "user")
    var usersList: MutableList<User> = mutableListOf()

    override fun toString(): String {
        return "Users(usersList=$usersList)"
    }
}

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = ["name", "birthday", "status", "hobbies"])
class User(
        @field:XmlAttribute()
        var name: String? = null,

        @field:XmlAttribute
        @field:XmlJavaTypeAdapter(DateSimpleFormatParseXML::class)
        var birthday: Date? = null,

        @field:XmlAttribute
        var status: Int? = null,

        @field:XmlElement(name = "hobby")
        @XmlElementWrapper
        val hobbies: MutableList<Hobby> = mutableListOf<Hobby>()
){
    fun addHobby(name: String, hours: Int) {
        hobbies.add(Hobby(name, hours))
    }

    fun addHobby(hobby: Hobby) {
        hobbies.add(hobby)
    }

    override fun toString(): String {
        return "User(name=$name, birthday=$birthday, status=$status, hobbies=$hobbies)"
    }

}

@XmlAccessorType(XmlAccessType.FIELD)
class Hobby(
        @field:XmlAttribute
        var hobby: String? = null,

        @field:XmlAttribute
        var hours: Int? = null
) {
    override fun toString(): String {
        return "Hobby(hobby='$hobby', hours=$hours)"
    }
}