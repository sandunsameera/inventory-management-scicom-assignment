package com.sandun.controller;

import com.sandun.model.Product;
import com.sandun.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Product> allProducts() {
        return productService.allProducts();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getById(@PathVariable Integer id) {
        Product product = productService.findById(id);
        return product;
    }

    @PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> searchProduct( @RequestParam(value = "brand") String brand,@RequestParam(value = "type") String type) {
     return productService.searchProduct(brand,type);
    }
}
