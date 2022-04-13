package com.example.helloworld.demo.controller

import com.example.helloworld.demo.domain.MemberRedis
import com.example.helloworld.demo.repository.MemberRedisRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/redis-test")
class RedisTestController(
    val memberRedisRepository: MemberRedisRepository
) {
    @GetMapping()
    fun getMemberRedisList(): ResponseEntity<Any> {
        val members = memberRedisRepository.findAll()
        val data = HashMap<String, Any>()
        data["list"] = members

        return ResponseEntity.ok(data)
    }

    @PostMapping()
    fun setMemberRedis(@RequestBody memberRedis: MemberRedis): ResponseEntity<Any> {
        memberRedisRepository.save(memberRedis)
        val data = HashMap<String, Any>()
        data["stats"] = "success"

        return ResponseEntity.ok(data)
    }
}