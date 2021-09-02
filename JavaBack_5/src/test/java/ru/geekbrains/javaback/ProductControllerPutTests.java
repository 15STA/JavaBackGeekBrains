package ru.geekbrains.javaback;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import ru.geekbrains.javaback.Entity.Product;
import ru.geekbrains.javaback.enums.CategoryType;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProductControllerPutTests extends BaseTest {
    static Product product;
    static Product productNew;

    @BeforeEach
    void setUp() throws IOException {
        product = new Product()
                .withTitle("melon")//
                .withPrice(80)
                .withCategoryTitle(CategoryType.FOOD.getTitle());
        Response<Product> addedProduct = productService.createProduct(product).execute();
        productNew = new Product()
                .withId(addedProduct.body().getId())
                .withTitle("melon")//
                .withPrice(60)
                .withCategoryTitle(CategoryType.FOOD.getTitle());
    }

    @Test
    void postProductTest() throws IOException {
        Response<Product> response = productService.modifyProduct(productNew).execute();
        assertThat(response.body().getPrice(), equalTo(productNew.getPrice()));
    }
}
