package com.example.helloworld.demo.request

data class DemoRequest(

    var id: Long = 0L,
    var name: String = "",
    var type: String = "",
    var password: String = "",
    var email: String = ""
)