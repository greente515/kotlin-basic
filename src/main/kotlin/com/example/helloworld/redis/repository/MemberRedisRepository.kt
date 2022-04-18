package com.example.helloworld.redis.repository

import com.example.helloworld.redis.domain.MemberRedis
import com.example.helloworld.redis.repository.querydsl.MemberCustomRepository
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRedisRepository : JpaRepository<MemberRedis, String>, MemberCustomRepository {
}