package com.example.unmarsh

import java.text.SimpleDateFormat
import java.util.*
import javax.xml.bind.annotation.adapters.XmlAdapter

class DateSimpleFormatParseXML : XmlAdapter<String, Date>() {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    override fun unmarshal(v: String?): Date {
        return dateFormat.parse(v)
    }
    override fun marshal(v: Date?): String {
        TODO("Not yet implemented")
    }
}