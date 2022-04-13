package com.example.helloworld.demo.repository.mapper

import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Repository
@Mapper
interface DemoSqlMapper {

    fun selectDemoCnt(): Int
}