package com.example.helloworld.demo.service

import com.example.helloworld.demo.domain.Demo

interface DemoService {

    fun findAll(): List<Demo>

    fun save(demo: Demo): Demo

    fun getDemoCnt(): HashMap<String, Any>
}