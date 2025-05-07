package com.farteaga.arkam.msvc_products.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetail {


    @Id //PK
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(columnDefinition = "TEXT") //name, length
    private String longDescription;

    private int stock;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id") // Foreign Key
    @JsonBackReference
    private Product product;

}
