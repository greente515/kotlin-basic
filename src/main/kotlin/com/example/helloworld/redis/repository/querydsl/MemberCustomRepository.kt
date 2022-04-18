package com.example.helloworld.redis.repository.querydsl

interface MemberCustomRepository {

    fun selectById(id: String): String?
}