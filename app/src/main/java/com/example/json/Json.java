package com.example.json;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface Json {
    @GET("comments")
    Call<List<Comment>> getComments();
}

