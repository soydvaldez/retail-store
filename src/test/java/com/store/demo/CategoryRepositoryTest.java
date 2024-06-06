package com.store.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;

import com.store.demo.api.categories.dto.CategoryDTO;
import com.store.demo.api.categories.entity.Category;
import com.store.demo.api.categories.repository.CategoryRepository;
import com.store.demo.api.products.dto.ProductDTO;
import com.store.demo.api.security.entity.User;
import com.store.demo.api.security.repository.UserRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldReturnAllCategoriesWithProductsPerCategory() {
        List<Category> categories = categoryRepository.findAll();
        assertThat(categories.size()).isEqualTo(3);
        categories.stream().map(this::create);
    }

    public CategoryDTO create(Category category) {
        System.out.println("break point @Test2");
        List<ProductDTO> productDTOs = category.getProducts()
                .stream()
                .map(product -> new ProductDTO(
                        product.getId(), product.getNombre(), product.getDescripcion(), product.getPrecio()))
                .collect(Collectors.toList());
        return new CategoryDTO(category.getId(), category.getNombre(), category.getDescripcion(), productDTOs);
    }

    public void testH2DataLoad() {
        List<User> entities = userRepository.findAll();
        assertFalse(entities.isEmpty());
        assertEquals("user", entities.get(0).getUsername());
    }
}
