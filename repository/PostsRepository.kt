package com.example.sampe.repository

import com.example.sampe.listener.IOResponse
import com.example.sampe.models.PostsResponseItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class PostsRepository : BaseRepository() {

    fun getPosts(ioResponse: IOResponse) = CoroutineScope(Dispatchers.IO).launch {
        apiCall(call = { service.getPosts() }, ioResponse)
    }
}