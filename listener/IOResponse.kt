package com.example.sampe.listener

interface IOResponse {
    fun <t> onResponse(response:ResponseModel<t>)
}