package com.example.fly_ci_cd.domain.post.post.repository

import com.example.fly_ci_cd.domain.post.post.entity.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long>