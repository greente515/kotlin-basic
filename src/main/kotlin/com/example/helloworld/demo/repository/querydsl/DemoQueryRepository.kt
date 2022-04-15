package com.example.helloworld.demo.repository.querydsl

import com.example.helloworld.demo.domain.Demo

interface DemoQueryRepository {

    fun getDemoDetails(id: Long): Demo?
}