package com.example.helloworld.redis.service

import com.example.helloworld.redis.domain.MemberRedis

interface MemberRedisService {

    fun findAll(): HashMap<String, Any>

    fun save(memberRedis: MemberRedis): MemberRedis

    fun getMemberById(id: String): String?
}