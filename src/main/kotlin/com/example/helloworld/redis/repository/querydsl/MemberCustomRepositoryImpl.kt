package com.example.helloworld.redis.repository.querydsl

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.redis.core.RedisTemplate

class MemberCustomRepositoryImpl(
    @Qualifier(value = "memberRedisTemplate") val redisTemplate: RedisTemplate<String, String>,
    private val mapper: ObjectMapper
): MemberCustomRepository {

    override fun selectById(id: String): String?  = redisTemplate.opsForValue().get(id)
}