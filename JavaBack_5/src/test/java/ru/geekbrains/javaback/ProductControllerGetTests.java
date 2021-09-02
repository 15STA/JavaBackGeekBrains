package ru.geekbrains.javaback;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import ru.geekbrains.javaback.Entity.Category;
import ru.geekbrains.javaback.Entity.Product;
import ru.geekbrains.javaback.enums.CategoryType;

import java.io.IOException;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProductControllerGetTests extends BaseTest {
    static Product product;
    static Integer id;
    @BeforeEach
    void setUp() throws IOException {
        product = new Product()
                .withTitle("grape")   //.withTitle(faker.food().dish())
                .withPrice(50)     //.withPrice((int) ((Math.random()+1)*100))
                .withCategoryTitle(CategoryType.FOOD.getTitle());
        Response<Product> addedProduct = productService.createProduct(product).execute();
        id = addedProduct.body().getId();
        System.out.println(id);
    }

    @Test
    void getProductByIdTest() throws IOException {
        Response<Product> response = productService
                .getProduct(id)
                .execute();
        assertThat(response.body().getTitle(), equalTo(product.getTitle()));
        assertThat (response.body().getCategoryTitle(), equalTo(CategoryType.FOOD.getTitle()));
        assertThat(response.body().getId(), equalTo(id));
    }
}
