//package com.harun.entity.models;
//
//import com.harun.entity.base.BaseEntity;
//import jakarta.persistence.*;
//import lombok.*;
//import java.util.List;
//
//@Entity
//@Table(name = "categories")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class Category extends BaseEntity<Long> {
//
//    @Column(nullable = false, unique = true)
//    private String name;
//
//    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Payment> payments;
//
//    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Invoice> invoices;
//}