package com.harun.entity.base.fields;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@MappedSuperclass
public abstract class BaseDate<T> extends BaseId<T> implements Serializable {

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    Date createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    Date updatedAt;
}
