package com.example.helloworld.demo.repository

import com.example.helloworld.demo.domain.Demo
import com.example.helloworld.demo.repository.querydsl.DemoQueryRepository
import org.springframework.data.jpa.repository.JpaRepository

interface DemoRepository : JpaRepository<Demo, Long>, DemoQueryRepository {
}