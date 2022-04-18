package com.example.helloworld.common.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import kotlin.properties.Delegates

//@ConstructorBinding
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
class RedisStandaloneProperties {
    lateinit var host: String
    var port by Delegates.notNull<Int>()
    var expire by Delegates.notNull<Long>()
}