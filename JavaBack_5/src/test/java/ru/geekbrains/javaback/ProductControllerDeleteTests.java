package ru.geekbrains.javaback;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.geekbrains.javaback.Entity.Category;
import ru.geekbrains.javaback.Entity.Product;
import ru.geekbrains.javaback.enums.CategoryType;
import ru.geekbrains.javaback.service.ProductService;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProductControllerDeleteTests extends BaseTest{
    static Product product;
    static Integer id;

    @BeforeEach
//@Test
    void setUp() throws IOException {
        product = new Product()
                .withTitle("grape")   //.withTitle(faker.food().dish())
                .withPrice(50)     //.withPrice((int) ((Math.random()+1)*100))
                .withCategoryTitle(CategoryType.FOOD.getTitle());
        Response<Product> addedProduct = productService.createProduct(product).execute();
        id = addedProduct.body().getId();
        System.out.println(id);
    }

  //  @Disabled
    @Test
    void deleteProductTest() throws IOException, InterruptedException {
        Call<Product> call = productService.deleteProduct(id);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
            }
            @Override
            public void onFailure(Call<Product> call, Throwable throwable) {
            }
        });
      //  Thread.sleep(2000);
        assertThat(call.isExecuted(), equalTo(true));
    }


}
