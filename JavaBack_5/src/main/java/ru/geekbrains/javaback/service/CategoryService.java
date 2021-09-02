package ru.geekbrains.javaback.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.geekbrains.javaback.Entity.Category;

public interface CategoryService {
    @GET("categories/{id}")
    Call<Category> getCategory(@Path("id") Integer id);
}
