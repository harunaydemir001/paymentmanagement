//package com.harun.entity.models;
//
//import com.harun.entity.base.BaseEntity;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//
//@Entity
//@Table(name = "invoices")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class Invoice extends BaseEntity<Long> {
//
//    @Column(nullable = false, unique = true)
//    private String invoiceNumber;
//
//    @Column(nullable = false)
//    private BigDecimal amount;
//
//    @Column(name = "due_date")
//    private LocalDate dueDate;
//
//    @Column(name = "is_paid")
//    private boolean isPaid = false;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "bank_user_id", nullable = false)
//    private BankUser bankUser;
//
//    @OneToOne(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Payment payment;
//}