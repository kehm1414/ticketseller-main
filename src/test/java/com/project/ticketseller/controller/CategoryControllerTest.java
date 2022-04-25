package com.project.ticketseller.controller;

import com.project.ticketseller.entity.Category;
import com.project.ticketseller.helpers.testOnly.WithMockCustomUser;
import com.project.ticketseller.repository.UserRepository;
import com.project.ticketseller.security.CustomOAuth2UserService;
import com.project.ticketseller.security.OAuth2LoginSuccessHandler;
import com.project.ticketseller.service.impl.CategoryServiceImpl;
import com.project.ticketseller.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryServiceImpl categoryService;


    @MockBean
    private CustomOAuth2UserService oAuth2UserService;
    @MockBean
    private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private UserServiceImpl userService;


    @Test
    @WithMockCustomUser(role="ROLE_USER")
    void userRoleCantAccessCategoriesList() throws Exception {

        String url = "/event-categories";

        ResultActions perform = mockMvc.perform(get(url).with(csrf()));
        perform.andDo(print()).andExpect((status().isForbidden()));
    }

    @Test
    @WithMockCustomUser()
    void categoriesList() throws Exception {

        String url = "/event-categories";

        ResultActions perform = mockMvc.perform(get(url).with(csrf()));
        perform.andDo(print()).andExpect((status().isOk()));
    }

    @Test
    @WithMockCustomUser()
    void newCategoryForm() throws Exception {

        String url = "/event-categories/new";

        ResultActions perform = mockMvc.perform(get(url).with(csrf()));
        perform.andDo(print()).andExpect((status().isOk()));
    }

    @Test
    @WithMockCustomUser()
    void saveCategory() throws Exception {

        Category category = Mockito.mock(Category.class);

        String url = "/event-categories/new";

        ResultActions perform = mockMvc.perform(post(url).flashAttr("category",category).with(csrf()));

        perform.andExpect(status().is(302));

    }

    @Test
    @WithMockCustomUser
    void editCategoryForm() throws Exception {

        String url = "/event-categories/edit/1";

        Category category = Mockito.mock(Category.class);

        Mockito.when(categoryService.getById(1l)).thenReturn(category);

        ResultActions perform = mockMvc.perform(get(url).with(csrf()));
        perform.andDo(print()).andExpect((status().isOk()));
    }

    @Test
    @WithMockCustomUser
    void updateCategory() throws Exception {
        String url = "/event-categories/1";

        Category category = Mockito.mock(Category.class);
        Category existingCat = Mockito.mock(Category.class);
        Mockito.when(categoryService.getById(1l)).thenReturn(existingCat);
        Mockito.when(category.getName()).thenReturn("MockName");

        ResultActions perform = mockMvc.perform(post(url).flashAttr("category", category).with(csrf()));
        perform.andDo(print()).andExpect((status().is(302)));
    }

    @Test
    @WithMockCustomUser
    void deleteCategory() throws Exception {
        String url = "/event-categories/1";

        ResultActions perform = mockMvc.perform(get(url).with(csrf()));
        perform.andDo(print()).andExpect((status().is(302)));
    }






}