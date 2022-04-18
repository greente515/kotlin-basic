package com.example.helloworld.demo.repository.querydsl

import com.example.helloworld.demo.domain.Demo
import com.example.helloworld.demo.domain.QDemo.demo
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

class DemoCustomRepositoryImpl(private val query:JPAQueryFactory): DemoCustomRepository {

    override fun getDemoDetails(id: Long): Demo? {
        return query
            .selectFrom(demo)
            .where(demo.id.eq(id))
            .fetchFirst()
    }

}