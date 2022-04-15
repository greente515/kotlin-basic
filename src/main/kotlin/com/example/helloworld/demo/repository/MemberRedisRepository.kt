package com.example.helloworld.demo.repository

import com.example.helloworld.demo.domain.MemberRedis
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRedisRepository : JpaRepository<MemberRedis, String> {
}