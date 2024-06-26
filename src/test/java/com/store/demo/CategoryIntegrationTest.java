package com.store.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import static  org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.store.demo.api.categories.entity.Category;
import com.store.demo.api.categories.service.CategoryService;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WithMockUser(username = "user", password = "password")
@AutoConfigureMockMvc
@DirtiesContext
public class CategoryIntegrationTest {
    @Autowired
    private WebApplicationContext context;
    
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CategoryService categoryService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    void shouldReturnAllCategoriesWhenIsRequested() throws Exception {
        this.mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.content", hasSize(3)));
    }

    // @Test
    void shouldReturnACategoryListedWhenIsRequested() throws Exception {
        // Datos de prueba
        Category category1 = new Category(1L, "Electronics", "Category for electronic devices and gadgets");
        Category category2 = new Category(2L, "Books", "Category for various types of books and novels");
        Category category3 = new Category(2L, "Clothing", "Category for clothing items and apparel");
        Pageable pageable = PageRequest.of(0, 10);
        Page<Category> page = new PageImpl<>(Arrays.asList(category1, category2, category3), pageable, 3);

        // Configurar el mock para el servicio
        when(categoryService.getCategories(PageRequest.of(0, 10))).thenReturn(page);

        this.mockMvc.perform(get("/api/categories")
                .param("page", "0")
                .param("size", "3")
                .param("sort", "id,desc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(3)))
                .andExpect(jsonPath("$.content[0].id", is(3)))
                .andExpect(jsonPath("$.content[0].nombre", is("Clothing")))
                .andExpect(jsonPath("$.content[0].descripcion", is("Category for clothing items and apparel")))
                .andExpect(jsonPath("$.content[1].id", is(2)))
                .andExpect(jsonPath("$.content[1].nombre", is("Books")))
                .andExpect(jsonPath("$.content[1].descripcion", is("Category for various types of books and novels")))
                .andExpect(jsonPath("$.content[2].id", is(1)))
                .andExpect(jsonPath("$.content[2].nombre", is("Electronics")))
                .andExpect(jsonPath("$.content[2].descripcion", is("Category for electronic devices and gadgets")))
                .andExpect(jsonPath("$.totalPages", is(1)))
                .andExpect(jsonPath("$.totalElements", is(3)));
    }

    // @Test
    void shouldReturnACategoryWhenIsRequested() throws Exception {
        this.mockMvc.perform(get("/api/categories/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.nombre").value("Clothing"))
                .andExpect(jsonPath("$.descripcion").value("Category for clothing items and apparel"));
    }

    @Test
    void shouldReturnCategoryNoTFoundWhenIsRequested() throws Exception {
        this.mockMvc.perform(get("/api/categories/100"))
                .andExpect(status().isNotFound());
    }

}
