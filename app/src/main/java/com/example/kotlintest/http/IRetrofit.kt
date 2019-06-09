package com.example.kotlintest.http

import com.example.kotlintest.Beens.Access_TokenBeens
import io.reactivex.Observable
import retrofit2.http.*

interface  IRetrofit{
    //    Oauth
    //获得accesstoken
    @FormUrlEncoded
    @Headers("Authorization:Basic eG5wb29sLWFwcDp4bnBvb2wtYXBwLXNlY3JldA==")
    @POST
    fun getAccessToken(
        @Url url: String, @Field("username") username: String, @Field("password") password: String, @Field(
            "grant_type"
        ) grant_type: String
    ): Observable<Access_TokenBeens>

}