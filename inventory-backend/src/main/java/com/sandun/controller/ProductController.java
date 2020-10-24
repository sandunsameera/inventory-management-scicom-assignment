package com.sandun.controller;

import com.sandun.model.Product;
import com.sandun.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Product> allProducts() {
        return productService.allProducts();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getById(@PathVariable Integer id) {
        Product product = productService.findById(id);
        return product;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product addProduct(@RequestBody Product newProduct) {
        Product product = productService.addProduct(newProduct);
        return product;
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product removeProduct(@PathVariable Integer id) {
        Product product = productService.removeProduct(id);
        return product;
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product updateProduct(@PathVariable Integer id, @RequestBody Product newProduct) {
        Product product = productService.updateProduct(id, newProduct);
        return product;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, params = {"desc"})
    public List<Product> searchedByDescription(@RequestParam(value = "desc") String description) {
        return productService.searchProductByDescription(description);
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, params = {"types"})
    public List<Product> search(@RequestParam(value = "types") String[] types) {
        return productService.searchByArray(types);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, params = {"brands"})
    public List<Product> searchByBrand(@RequestParam(value = "brands") String[] brands) {
        return productService.searchByBrandArray(brands);
    }
}
