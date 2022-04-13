package com.example.helloworld.demo.domain

import org.springframework.data.redis.core.RedisHash
import javax.persistence.Id

@RedisHash("member")    //redis 에 hash 포맷으로 저장
data class MemberRedis(
    @Id
    val id: String? = null,
    val name: String? = null,
    val email: String? = null,
    var age: Int? = 0
)
