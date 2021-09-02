package ru.geekbrains.javaback;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import ru.geekbrains.javaback.Entity.Category;
import ru.geekbrains.javaback.Entity.Product;
import ru.geekbrains.javaback.enums.CategoryType;
import ru.geekbrains.javaback.service.CategoryService;
import ru.geekbrains.javaback.service.ProductService;
import ru.geekbrains.javaback.utils.RetrofitUtils;

import java.io.IOException;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProductControllerPostTests extends BaseTest {
//    Faker faker = new Faker();
    static Product product;

    @BeforeEach
    void setUp() {
        product = new Product()
                .withTitle("sugar")//
                .withPrice((int) ((Math.random()+1)*100)) //.withTitle(faker.food().dish())
                .withCategoryTitle(CategoryType.FOOD.getTitle());
    }

    @Test
    void postProductTest() throws IOException {
        Response<Product> response = productService.createProduct(product).execute();
        assertThat(response.body().getTitle(), equalTo(product.getTitle()));
        assertThat(response.body().getPrice(), equalTo(product.getPrice()));
        assertThat(response.body().getCategoryTitle(), equalTo(product.getCategoryTitle()));
    }


}
