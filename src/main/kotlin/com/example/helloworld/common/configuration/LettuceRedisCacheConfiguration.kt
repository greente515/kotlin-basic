//package com.example.helloworld.common.configuration
//
//import com.example.helloworld.common.properties.RedisCacheKeyProperties
//import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.data.redis.cache.RedisCacheConfiguration
//import org.springframework.data.redis.serializer.RedisSerializationContext
//import java.time.Duration
//
//@Configuration(proxyBeanMethods = false)
//class LettuceRedisCacheConfiguration {
//
//    @Bean
//    fun redisCacheManagerCustomizer() = RedisCacheManagerBuilderCustomizer {
//        builder -> builder.withInitialCacheConfigurations(getCacheKeyValuesMap())
//    }
//
//    @Bean
//    fun cacheConfiguration(): RedisCacheConfiguration? {
//        return RedisCacheConfiguration.defaultCacheConfig()
//            .entryTtl(Duration.ofMinutes(60))
//            .disableCachingNullValues()
//            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(GenericJackson2JsonRedisSerializer()))
//
//    }
//
//    fun getCacheKeyValuesMap() = RedisCacheKeyProperties.values()
//        .associate { it.name to RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(it.ttl)) }
//}