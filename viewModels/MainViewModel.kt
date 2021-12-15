package com.example.sampe.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sampe.listener.IOResponse
import com.example.sampe.listener.ResponseModel
import com.example.sampe.repository.PostsRepository

class MainViewModel : ViewModel() {
    private var postsRepository = PostsRepository()
    private var responseModel = MutableLiveData<ResponseModel<*>>()
    private val progressLiveData = MutableLiveData<Boolean>()

    fun getPost() {
        progressLiveData.postValue(true)
        postsRepository.getPosts(object : IOResponse {
            override fun <t> onResponse(response: ResponseModel<t>) {
                progressLiveData.postValue(false)
                responseModel.postValue(response)
            }

        })
    }

    fun observePosts() = responseModel

    fun observeProgress() = progressLiveData
}