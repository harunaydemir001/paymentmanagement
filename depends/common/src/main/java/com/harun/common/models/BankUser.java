package com.harun.common.models;


import com.harun.common.base.BaseDate;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bank_users")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BankUser extends BaseDate<Long> {
    String username;

    @OneToMany(mappedBy = "bankUser", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Account> accounts = new ArrayList<>();
}
