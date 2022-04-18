package com.example.helloworld.demo.repository

import com.example.helloworld.demo.domain.Demo
import com.example.helloworld.demo.repository.querydsl.DemoCustomRepository
import org.springframework.data.jpa.repository.JpaRepository

interface DemoRepository : JpaRepository<Demo, Long>, DemoCustomRepository {
}