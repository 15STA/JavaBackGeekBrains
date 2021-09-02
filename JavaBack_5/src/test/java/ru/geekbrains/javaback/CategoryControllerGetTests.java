package ru.geekbrains.javaback;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import ru.geekbrains.javaback.Entity.Category;
import ru.geekbrains.javaback.enums.CategoryType;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CategoryControllerGetTests extends BaseTest {

    @Test
    void getCategoryByIdTest() throws IOException {
        Integer id = CategoryType.ELECTRONIC.getId();
        Response<Category> response = categoryService
                .getCategory(id)
                .execute();
        assertThat(response.body().getTitle(), equalTo(CategoryType.ELECTRONIC.getTitle()));
        assertThat(response.body().getId(), equalTo(id));
    }

    @Test
    void getErrorCategoryByIdTest() throws IOException { //если категория не существует, то ошибка 404
        Integer wrongCategoryId = CategoryType.GAMES.getId();
        Response<Category> response = categoryService
                .getCategory(wrongCategoryId)
                .execute();
        assertThat(response.code(), equalTo(404));
    }
}
