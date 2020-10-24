package com.sandun.service.implementation;

import com.sandun.model.Product;
import com.sandun.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImplementation implements ProductService {

    private static final List<Product> products = new ArrayList<>();

    static {
//   Innitialize product list

        Product product1 = new Product(1, "Samsung", "TV", 800.00, new Date(), "32 Inch LED TV");
        Product product2 = new Product(2, "LG", "Fridge", 700.00, new Date(), "Double Door Fridge");
        Product product3 = new Product(3, "Singer", "Fridge", 200.00, new Date(), "Singer inverter fridge");
        Product product4 = new Product(4, "Samsung", "TV", 900.00, new Date(), "48 Inch LED Tv");
        Product product5 = new Product(5, "LG", "Fridge", 1600.00, new Date(), "Double door fridge");
        Product product6 = new Product(6, "TCL", "TV", 800.00, new Date(), "48 Inch LED Tv");
        Product product7 = new Product(7, "TCL", "TV", 1200.00, new Date(), "96 Inch LED Tv");
        Product product8 = new Product(8, "Samsung", "Fridge", 1200.00, new Date(), "96 Inch LED Tv");

        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);
        products.add(product7);
        products.add(product8);


    }

    @Override
    public List<Product> allProducts() {
        return products;
    }

    @Override
    public Product findById(int Id) {
        for (Product product : products) {
            if (product.getId() == Id) {
                return product;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Override
    public Product addProduct(Product product) {
        if (product.getBrand().isEmpty() || product.getPrice() == 0 || product.getType().isEmpty() || product.getExpireDate().before(new Date()) || product.getDescription().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            products.add(product);
            return product;
        }
    }

    @Override
    public Product removeProduct(int productId) {
        for (Product product : products) {
            if (product.getId() == productId) {
                products.remove(product);
                return product;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Override
    public Product updateProduct(int id, Product newProduct) {
        for (Product product : products) {
            if (product.getId() == id) {
                product.setId(newProduct.getId());
                product.setDescription(newProduct.getDescription());
                product.setExpireDate(newProduct.getExpireDate());
                product.setType(newProduct.getType());
                product.setPrice(newProduct.getPrice());
                return newProduct;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Override
    public List<Product> searchProductByDescription(String description) {
        List<Product> searchedProducts = new ArrayList<>();

        for (Product product : products) {
            if (product.getDescription().toLowerCase().contains(description.toLowerCase())) {
                searchedProducts.add(product);
            }
        }
        return searchedProducts;
    }

    @Override
    public List<Product> searchByArray(String[] types) {
        List<Product> searchedProducts = new ArrayList<>();
        for (Product product : products) {
            for (String type : types) {
                if (product.getType().equalsIgnoreCase(type)) {
                    searchedProducts.add(product);
                }
            }
        }
        return searchedProducts;
    }




    @Override
    public List<Product> searchByBrandArray(String[] brands) {
        List<Product> searchedProducts = new ArrayList<>();

        for (Product product : products) {
            for (String brand : brands) {
                if (product.getBrand().equalsIgnoreCase(brand)) {
                    searchedProducts.add(product);
                }
            }
        }
        return searchedProducts;
    }
}
