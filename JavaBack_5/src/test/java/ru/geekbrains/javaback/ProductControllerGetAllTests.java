package ru.geekbrains.javaback;

import org.junit.jupiter.api.Test;
import retrofit2.Response;
import ru.geekbrains.javaback.Entity.Product;

import java.io.IOException;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProductControllerGetAllTests extends BaseTest {

    @Test
    void productsCategoryTitleIsNotNull() throws IOException {  //у всех продуктов Категория не NULL
        Response<ArrayList<Product>> response = productService.getProducts().execute();
        System.out.println(response.body());
        response.body().forEach(productItem -> {assertThat(productItem.getCategoryTitle(), notNullValue());});
    }
}
