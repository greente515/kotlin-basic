package com.example.helloworld.demo.controller

import com.example.helloworld.demo.domain.Demo
import com.example.helloworld.demo.service.Impl.DemoServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/demo")
class DemoController {

    private lateinit var demoService: DemoServiceImpl

    /**
     * HelloWorld TEST
     */
    @GetMapping("/hello")
    fun index(): ResponseEntity<String> {
        val hello = "Hello World!"

        return ResponseEntity.ok(hello)
    }

    /**
     * findAll()
     */
    @GetMapping()
    fun getDemos(): ResponseEntity<*> {
        val demos = demoService.findAll()

        return ResponseEntity.ok(demos)
    }

    /**
     * save
     */
    @PostMapping()
    fun setDemo(@RequestBody demo: Demo): ResponseEntity<*> {
        val res = demoService.save(demo)

        return ResponseEntity.ok(res)
    }
}