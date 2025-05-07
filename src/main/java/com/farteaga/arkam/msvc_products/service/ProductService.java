package com.farteaga.arkam.msvc_products.service;

import com.farteaga.arkam.msvc_products.model.Product;
import com.farteaga.arkam.msvc_products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    //@Autowired
    private final ProductRepository productRepository;


    public List<Product> allProduct() {
        return  productRepository.findAll();
    }

    public Product newProduct(Product product){
        return productRepository.save(product);
    }


    public Product getProduct(Long id){
      return  productRepository.findById(id).orElse(null);
    }

    public Product updateProduct(Long id, Product product){
        Optional<Product> productExist = productRepository.findById(id);
        if (productExist.isPresent()){
            product.setId(id);
            return productRepository.save(product);
        }

        return null;
    }

    public boolean deleteProduct(Long id){
        Optional<Product> productExist = productRepository.findById(id);
        if (productExist.isPresent()){
            productRepository.deleteById(id);
            return true;
        }

        return false;
    }

    public List<Product> searchProducts(String term) {
        return productRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(term, term);
    }

    public List<Product> getAllProductsSorted() {
        return productRepository.findAllByOrderByNameAsc();
    }

    public List<Product> searchProductsByPriceRange(Double minPrice, Double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }




}