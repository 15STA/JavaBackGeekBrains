package ru.geekbrains.javaback;

import org.junit.jupiter.api.BeforeAll;
import retrofit2.Retrofit;
import ru.geekbrains.javaback.service.CategoryService;
import ru.geekbrains.javaback.service.ProductService;
import ru.geekbrains.javaback.utils.RetrofitUtils;

public abstract class BaseTest {

    static Retrofit client;
    static ProductService productService;
    static CategoryService categoryService;

    @BeforeAll
    static void beforeAll() {
        client = RetrofitUtils.getRetrofit();
        productService = client.create(ProductService.class);
        categoryService = client.create(CategoryService.class);
    }
}
