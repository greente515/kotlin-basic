package com.example.helloworld.common.configuration

import io.lettuce.core.ReadFrom
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisStaticMasterReplicaConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory

/**
 * redis primary, reader 구성
 *
 */
//@Configuration
//class RedisMasterReplicaConfig {
//
//    private val primaryHost = ""
//    private val primayPost = 0
//    private val readerHost = ""
//    private val readerPort = 0
//
//    /**
//     * Redis Connection 설정
//     * - Write : Master Redis
//     * - Read : Replica Redis
//     * @return LettuceConnectionFactory
//     */
//    @Bean
//    fun redisConnectionFactory(): LettuceConnectionFactory {
//        //Primary, Replica Config
//        val elasticCache = RedisStaticMasterReplicaConfiguration(primaryHost, primayPost)
//        elasticCache.addNode(readerHost, readerPort)
//
//        // Lettuce 사용
//        val clientConfig = LettuceClientConfiguration.builder()
//            .readFrom(ReadFrom.REPLICA_PREFERRED) //Read 는 Replica Redis 를 바라보도록 설정
//            .build()
//
//        return LettuceConnectionFactory(elasticCache, clientConfig)
//    }
//}