package com.example.fly_ci_cd.domain.home.home.controller

import com.example.fly_ci_cd.global.extensions.logger
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import java.net.InetAddress

@Controller
class HomeController {

    private val logger = logger()
    
    @GetMapping
    @ResponseBody
    fun main(): String {
        val localhost = InetAddress.getLocalHost()
        val hostname = localhost.hostName

        println(hostname) // 모든 환경
        logger.info("hostname: $hostname") // info 레벨 설정 환경
        logger.debug("hostname: $hostname") // debug 레벨 설정 환경

        return "홈, hostname: $hostname"
    }
}