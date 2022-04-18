package com.example.helloworld.redis.controller

import com.example.helloworld.redis.domain.MemberRedis
import com.example.helloworld.redis.repository.MemberRedisRepository
import com.example.helloworld.redis.service.Impl.MemberRedisServiceImpl
import com.example.helloworld.redis.service.MemberRedisService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/redis-test")
class RedisTestController(
    private val memberRedisService: MemberRedisServiceImpl
) {
    @GetMapping()
    fun getMemberRedisList(): ResponseEntity<Any> {
        val members = memberRedisService.findAll()

        return ResponseEntity.ok(members)
    }

    @PostMapping()
    fun setMemberRedis(@RequestBody memberRedis: MemberRedis): ResponseEntity<Any> {
        val member = memberRedisService.save(memberRedis)
        val data = HashMap<String, Any>()
        data["stats"] = "success"

        return ResponseEntity.ok(data)
    }

    @GetMapping("/{id}")
    fun getMember(@PathVariable id: String): ResponseEntity<Any>{
        val member = memberRedisService.getMemberById(id)

        return ResponseEntity.ok(member)
    }
}