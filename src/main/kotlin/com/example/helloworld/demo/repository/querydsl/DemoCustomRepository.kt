package com.example.helloworld.demo.repository.querydsl

import com.example.helloworld.demo.domain.Demo

interface DemoCustomRepository {

    fun getDemoDetails(id: Long): Demo?
}