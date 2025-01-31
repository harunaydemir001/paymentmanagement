package com.harun.entity.models;

import com.harun.entity.base.BaseDate;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Table(name = "notifications")
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class Notification extends BaseDate<Long> {

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;
}
