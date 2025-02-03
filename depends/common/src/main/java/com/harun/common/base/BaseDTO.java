package com.harun.common.base;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class BaseDTO<T> {
    T id;

    Integer version;

    Date createdAt;

    Date updatedAt;
}
