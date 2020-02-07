package com.example.moviedb

import com.example.moviedb.RestApi.ApiClient
import com.example.pet.RestApi.RestApi

class Rest {
    fun getRest():RestApi{
        return ApiClient.getClient().create(RestApi::class.java)
    }
}