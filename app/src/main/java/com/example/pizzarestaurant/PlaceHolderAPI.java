package com.example.pizzarestaurant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PlaceHolderAPI {
    @GET("/StWODX/uasresto/")
    Call<List<Post>> getPosts();
}
