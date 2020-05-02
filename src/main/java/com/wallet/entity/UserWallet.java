package com.wallet.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users_wallet")
@Data
public class UserWallet extends AbstractEntity<Long> {

    @JoinColumn(name = "users", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User users;

    @JoinColumn(name = "wallet", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Wallet wallet;
}
