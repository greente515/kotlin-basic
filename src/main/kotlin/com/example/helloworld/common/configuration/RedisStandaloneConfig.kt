package com.example.helloworld.common.configuration

import com.example.helloworld.common.properties.RedisStandaloneProperties
import com.sun.tracing.dtrace.ProviderAttributes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.cache.CacheKeyPrefix
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration

/**
 * 단일 redis
 */
@Configuration
class RedisStandaloneConfig(
    val redisProperties: RedisStandaloneProperties
) {
    /**
     * Redis Connection 설정
     * @return LettuceConnectionFactory
     */
    @Bean
    fun redisConnectionFactory(): LettuceConnectionFactory{
        val configuration = RedisStandaloneConfiguration()
        configuration.hostName = redisProperties.host
        configuration.port = redisProperties.port

        return LettuceConnectionFactory(configuration)
    }

    /**
     * RedisTemplate 설정
     * @return RedisTemplate<String, Member>
     */
    @Primary
    @Bean(name = ["memberRedisTemplate"])
    fun memberRedisTemplate(): RedisTemplate<String, String>{
        val redisTemplate = RedisTemplate<String, String>()
        redisTemplate.setConnectionFactory(redisConnectionFactory())

        //아래의 설정값이 없으면 스트링에서 조회할 때는 값이 정상으로 보이지만 redis-cli 로 조회하면 `xec\x83\x98\xed\x94\x8c1` 형태로 보여짐
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.valueSerializer = StringRedisSerializer()

        return redisTemplate
    }

    /**
     * Redis Cache 를 관리
     * @return RedisCacheManager
     */
    fun memberManager(): RedisCacheManager{
        val configuration = RedisCacheConfiguration.defaultCacheConfig()
            .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(StringRedisSerializer()))
            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(GenericJackson2JsonRedisSerializer()))  //json 형식으로 value 저장
            .computePrefixWith(CacheKeyPrefix.simple()) //key 앞에 '::' tkqdla
            .disableCachingNullValues() //null 값 금지
            .entryTtl(Duration.ofHours(redisProperties.expire)) //캐싱 유지 시간 설정

        return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory())
            .cacheDefaults(configuration)
            .build()
    }
}