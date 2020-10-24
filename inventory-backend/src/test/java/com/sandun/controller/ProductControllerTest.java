package com.sandun.controller;


import com.sandun.model.Product;
import com.sandun.service.ProductService;
import javafx.application.Application;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

    }

    @Test
    public void getProducts() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk());

    }

    @Test
    public void getSingleProduct() throws Exception {
        Integer id = 1;
        when(productService.findById(id)).thenReturn(new Product());
        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk());
    }


    @Test
    public void deleteSingleProduct() throws Exception {
        Integer id = 1;
        when(productService.findById(id)).thenReturn(new Product());
        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void getProductsByType() throws Exception{
        String[] types = {"tv","fridge"};

        mockMvc.perform((get("/products?" + types)))
                .andExpect(status().isOk());
    }

    @Test
    public void getProductsByBrand() throws Exception{
        String[] brands={"samsung","tcl"};
        mockMvc.perform(get("/products?"+brands))
                .andExpect(status().isOk());
    }

    @Test
    public void searchByDescription() throws Exception{
        String desc = "inch";

        mockMvc.perform(get("/products?"+desc))
                .andExpect(status().isOk());
    }

    @Test
    public void addProduct() throws Exception{
       String json =  "{\n" +
               "  \"id\": \"10\",\n" +
               "  \"brand\": \"Samsung\",\n" +
               "  \"type\": \"Tv\",\n" +
               "  \"price\": \"200.00\",\n" +
               "  \"expireDate\": \"16031713338400\",\n" +
               "  \"description\": \"good tv\"\n" +
               "}";

       mockMvc.perform(post("/products")
               .contentType(MediaType.APPLICATION_JSON_VALUE)
               .content(json))
               .andExpect(status().isOk());
    }

    @Test
    public void editProduct() throws Exception{
        Integer id =1;
        String json =  "{\n" +
                "  \"id\": \"10\",\n" +
                "  \"brand\": \"Samsung\",\n" +
                "  \"type\": \"Tv\",\n" +
                "  \"price\": \"200.00\",\n" +
                "  \"expireDate\": \"16031713338400\",\n" +
                "  \"description\": \"good tv\"\n" +
                "}";
        mockMvc.perform(put("/products/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json)).andExpect(status().isOk());
    }


}
