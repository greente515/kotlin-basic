package com.example.helloworld.demo.repository

import com.example.helloworld.demo.domain.Demo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DemoRepository : JpaRepository<Demo, Long> {
}