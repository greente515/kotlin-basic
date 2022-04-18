package com.example.helloworld.redis.service.Impl

import com.example.helloworld.redis.domain.MemberRedis
import com.example.helloworld.redis.repository.MemberRedisRepository
import com.example.helloworld.redis.service.MemberRedisService
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class MemberRedisServiceImpl(
    private val memberRedisRepository: MemberRedisRepository
): MemberRedisService {

    override fun findAll(): HashMap<String, Any> {
        val members = memberRedisRepository.findAll()
        val data = HashMap<String, Any>()
        data["list"] = members

        return data
    }

    override fun save(memberRedis: MemberRedis): MemberRedis {
        return memberRedisRepository.save(memberRedis)
    }

    @Cacheable(value = ["member"], key = "#id", cacheManager = "memberRedisTemplate")
    override fun getMemberById(id: String): String? {
        return memberRedisRepository.selectById(id)
    }

}