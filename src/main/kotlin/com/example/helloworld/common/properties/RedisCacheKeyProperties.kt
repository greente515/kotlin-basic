package com.example.helloworld.common.properties

enum class RedisCacheKeyProperties(
    val description: String,
    val ttl: Long,
    val key: Boolean,
    val palceholder: String,
    val dpOrderNumber: Int
) {
    cached_default(
        "시스템 유지 관리 캐시",
        60 * 60 * 24L,
        true,
        "KEY",
        0
    ),  //second s*m*h
    cached_company_id(
        "회사 정보 캐시",
        60 * 60 * 24L,
        true,
        "KEY",
        0
    )
}