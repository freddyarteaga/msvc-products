package com.farteaga.arkam.msvc_products.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Entity //Entidad: Objeto persistente/Instancia DB
@Table(name = "products")
@Data //libreria que automatiza setters, getters, constructores, etc.
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id //PK
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2)
    private String name;

    @Min(1)
    private double price;

    @NotBlank
    @Column(columnDefinition = "TEXT") //name, length
    private String description;

    @OneToOne(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ProductDetail detail;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    //Asignan fechas automáticas de creación y actualización.
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }



}