package com.sandun.service;

import com.sandun.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> allProducts();

    Product findById(int id);

    Product addProduct(Product product);

    Product removeProduct(int id);

    Product updateProduct(int id,Product product);

//    List<Product> searchProductByBrand(String brand);

    List<Product> searchProductByDescription(String description);

//    List<Product> searchProductByType(String type);

    List<Product> searchByArray(String[] types);

    List<Product> searchByBrandArray(String[] brands);
}
