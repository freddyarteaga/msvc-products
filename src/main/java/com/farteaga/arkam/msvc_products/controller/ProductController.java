package com.farteaga.arkam.msvc_products.controller;

import com.farteaga.arkam.msvc_products.model.Product;
import com.farteaga.arkam.msvc_products.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(){
        List<Product> products = productService.allProduct();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    @PostMapping("/withoutDTO")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product){
        Product createdProduct = productService.newProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }



    // localhost:8080/api/products/1
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable long id) { //id=1
        Product product = productService.getProduct(id);
        if (product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    // localhost:8080/api/products/1
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody Product product){
        Product updateproduct = productService.updateProduct(id, product);
        if (updateproduct == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(updateproduct, HttpStatus.OK);
    }

    // localhost:8080/api/products/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (productService.deleteProduct(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String term) {
        List<Product> products = productService.searchProducts(term);
        return products.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(products);
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<Product>> getSortedProducts() {
        List<Product> products = productService.getAllProductsSorted();
        return products.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(products);
    }

    @GetMapping("/searchByPrice")
    public ResponseEntity<List<Product>> searchProductsByPrice(
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice) {

        List<Product> products = productService.searchProductsByPriceRange(minPrice, maxPrice);
        return products.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(products);
    }




}