package com.example.helloworld.demo.repository.querydsl

import com.example.helloworld.demo.domain.Demo
import com.example.helloworld.demo.domain.QDemo
import com.example.helloworld.demo.domain.QDemo.demo
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class DemoQueryRepositoryImpl(private val query:JPAQueryFactory): DemoQueryRepository {

    override fun getDemoDetails(id: Long): Demo? {
        return query
            .selectFrom(demo)
            .where(demo.id.eq(id))
            .fetchFirst()
    }

}