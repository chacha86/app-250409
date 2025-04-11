package com.example.fly_ci_cd.global.app

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment

@Configuration
class AppConfig {

    companion object {
        private lateinit var objectMapper: ObjectMapper
        private lateinit var environment: Environment
        private lateinit var siteBackUrl: String
        private lateinit var siteFrontUrl: String

        
        fun getObjectMapper(): ObjectMapper = objectMapper

        fun getSiteBackUrl(): String = siteBackUrl
        
        fun getSiteFrontUrl(): String = siteFrontUrl

        fun getTempDirPath(): String = System.getProperty("java.io.tmpdir")

        
        val isNotProd: Boolean
            get() = !isProd

        
        val isProd: Boolean
            get() = environment.matchesProfiles("prod")

        
        val isDev: Boolean
            get() = environment.matchesProfiles("dev")

        
        val isTest: Boolean
            get() = environment.matchesProfiles("test")

    }

    @Value("\${custom.site.backUrl}")
    fun setSiteBackUrl(siteBackUrl: String) {
        Companion.siteBackUrl = siteBackUrl
    }

    @Value("\${custom.site.frontUrl}")
    fun setSiteFrontUrl(siteFrontUrl: String) {
        Companion.siteFrontUrl = siteFrontUrl
    }

    @Autowired
    fun setEnvironment(environment: Environment) {
        Companion.environment = environment
    }

    @Autowired
    fun setObjectMapper(objectMapper: ObjectMapper) {
        Companion.objectMapper = objectMapper
    }
}
