package com.example.helloworld.demo.service.Impl

import com.example.helloworld.demo.domain.Demo
import com.example.helloworld.demo.repository.DemoRepository
import com.example.helloworld.demo.repository.mapper.DemoSqlMapper
import com.example.helloworld.demo.service.DemoService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class DemoServiceImpl(
    private val demoRepository: DemoRepository,
    private val demoSqlMapper: DemoSqlMapper,
): DemoService {

    override fun findAll(): List<Demo> {
        return demoRepository.findAll()
    }

    @Transactional
    override fun save(demo: Demo): Demo {
        return demoRepository.save(demo)
    }

    override fun getDemoCnt(): HashMap<String, Any> {
        val cnt = demoSqlMapper.selectDemoCnt()

        val data = HashMap<String, Any>()
        data["Cnt"] = cnt

        return data
    }
}