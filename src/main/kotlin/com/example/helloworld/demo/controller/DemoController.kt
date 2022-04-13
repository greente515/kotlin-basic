package com.example.helloworld.demo.controller

import com.example.helloworld.demo.domain.Demo
import com.example.helloworld.demo.repository.DemoRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/demo")
class DemoController(
    val demoRepository: DemoRepository
) {

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
        val demos = demoRepository.findAll()

        return ResponseEntity.ok(demos)
    }

    /**
     * save
     */
    @PostMapping()
    fun setDemo(@RequestBody demo: Demo): ResponseEntity<*> {
        val res = demoRepository.save(demo)

        return ResponseEntity.ok(res)
    }
}