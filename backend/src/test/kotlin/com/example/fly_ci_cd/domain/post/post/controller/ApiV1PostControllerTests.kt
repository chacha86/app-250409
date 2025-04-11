package com.example.fly_ci_cd.domain.post.post.controller

import com.example.fly_ci_cd.domain.post.post.entity.Post
import com.example.fly_ci_cd.domain.post.post.service.PostService
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
class ApiV1PostControllerTests {

    @Autowired
    private lateinit var mvc: MockMvc

    @Autowired
    private lateinit var postService: PostService

    private fun checkPosts(posts: List<Post>, resultActions: ResultActions) {
        posts.forEachIndexed { i, post ->
            resultActions
                .andExpect(jsonPath("$[$i]").exists())
                .andExpect(jsonPath("$[$i].id").value(post.id))
                .andExpect(jsonPath("$[$i].title").value(post.title))
        }
    }

    @Test
    @DisplayName("글 다건 조회 - 공개 글 목록")
    fun items1() {

        val resultActions = mvc
            .perform(
                get("/api/v1/posts")
            )
            .andDo(MockMvcResultHandlers.print())

        // then
        resultActions
            .andExpect(status().isOk())
            .andExpect(handler().handlerType(ApiV1PostController::class.java))
            .andExpect(handler().methodName("getItems"))

        val posts = postService.findAll()

        checkPosts(posts, resultActions)
    }
}