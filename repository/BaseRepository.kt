package com.example.sampe.repository

import android.util.Log
import com.example.sampe.listener.IOResponse
import com.example.sampe.listener.ResponseModel
import com.example.sampe.network.RetrofitModule
import retrofit2.Call
import retrofit2.Response
import java.io.IOException
import java.lang.Exception

private const val TAG = "BaseRepository"

abstract class BaseRepository {

    val service = RetrofitModule.provideRetrofit()


    protected suspend fun <T : Any> apiCall(
        call: suspend () -> Response<T>,
        ioResponse: IOResponse
    ) {
        val response: Response<T>
        val responseModel = ResponseModel<T>()
        try {
            response = call.invoke()
        } catch (t: Throwable) {
            responseModel.isSuccess = false
            if (t is IOException){
                responseModel.message="No internet connection found!"
            }else{
                responseModel.message = t.message
            }
            ioResponse.onResponse(responseModel)
            return
        }

        try {
            if (!response.isSuccessful) {
                responseModel.isSuccess = false
                responseModel.message = response.message()
            } else {
                responseModel.isSuccess = true
                responseModel.model = response.body()
            }
        } catch (exp: Exception) {
            responseModel.isSuccess = false
            responseModel.message = exp.message
        }

        ioResponse.onResponse(responseModel)
    }

}