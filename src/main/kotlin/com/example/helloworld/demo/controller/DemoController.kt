package com.example.helloworld.demo.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/demo")
class DemoController {

    @GetMapping()
    fun index(): ResponseEntity<String>{
        val hello = "Hello World!"

        return ResponseEntity.ok(hello)
    }
}