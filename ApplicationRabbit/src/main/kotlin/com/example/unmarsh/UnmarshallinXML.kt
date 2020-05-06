package com.example.unmarsh

import com.example.jdbc.UsersDbEntityDAO
import org.springframework.stereotype.Component
import java.io.FileInputStream
import javax.xml.bind.JAXBContext
import javax.xml.bind.Unmarshaller

@Component
class UnmarshallinXML(
        val usersDbEntityDAO: UsersDbEntityDAO
) {
    val jaxb = JAXBContext.newInstance(Users::class.java)
    fun unmarshalling(path: String) : Boolean{
        val stream = FileInputStream (path)
        try{
            val unmarshaller : Unmarshaller = jaxb.createUnmarshaller()
            val users = unmarshaller.unmarshal(stream) as Users
            stream.close()
            usersDbEntityDAO.update(users)
            return  true
        }
        catch(e: Exception){
            stream.close()
            return false
        }
    }
}