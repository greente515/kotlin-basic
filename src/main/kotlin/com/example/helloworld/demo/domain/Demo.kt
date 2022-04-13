package com.example.helloworld.demo.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "demo")
data class Demo(
    @Id
    @Column(name = "id")
    var id: Long = 0L,

    @Column(name = "name")
    var name: String = "",

    @Column(name = "type")
    var type: String = "",

    @Column(name = "password")
    var password: String = "",

    @Column(name = "email")
    var email: String = ""
)
