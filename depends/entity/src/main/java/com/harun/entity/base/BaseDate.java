package com.harun.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@MappedSuperclass
public abstract class BaseDate<T> extends BaseId<T> implements Serializable {

    @Column(nullable = false, updatable = false)
    Date createdAt;

    @Column(nullable = false)
    Date updatedAt;

    @PrePersist
    private void onCreate() {
        setCreatedAt(new Date());
        setUpdatedAt(new Date());
    }

    @PreUpdate
    private void onUpdate() {
        setUpdatedAt(new Date());
    }
}
