package com.farteaga.arkam.msvc_products.repository;

import com.farteaga.arkam.msvc_products.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);
    List<Product> findAllByOrderByNameAsc();

    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);


}

