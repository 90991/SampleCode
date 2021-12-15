package com.example.sampe.network

import com.example.sampe.models.PostsResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface DataService {

    @GET("posts")
    suspend fun getPosts(): Response<List<PostsResponseItem>>

}