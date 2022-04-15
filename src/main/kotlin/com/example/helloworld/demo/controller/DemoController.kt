package com.example.helloworld.demo.controller

import com.example.helloworld.demo.domain.Demo
import com.example.helloworld.demo.service.Impl.DemoServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.xml.ws.Response

@RestController
@RequestMapping("/demo")
class DemoController(
    private val demoService: DemoServiceImpl
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
        val demos = demoService.findAll()
        val data = HashMap<String, Any>()
        data["list"] = demos

        return ResponseEntity.ok(data)
    }

    /**
     * save
     */
    @PostMapping()
    fun setDemo(@RequestBody demo: Demo): ResponseEntity<*> {
        val res = demoService.save(demo)

        return ResponseEntity.ok(res)
    }

    /**
     * querydsl
     */
    @GetMapping("/{id}")
    fun getDemoDetails(@PathVariable id: Long): ResponseEntity<*> {
        val res = demoService.getDemoDetails(id)

        return ResponseEntity.ok(res)
    }
}