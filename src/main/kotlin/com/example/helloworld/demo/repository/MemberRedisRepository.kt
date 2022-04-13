package com.example.helloworld.demo.repository

import com.example.helloworld.demo.domain.MemberRedis
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRedisRepository : JpaRepository<MemberRedis, String> {
}