package com.wallet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users_wallet")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWallet extends AbstractEntity<Long> {

    @JoinColumn(name = "users", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User users;

    @JoinColumn(name = "wallet", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Wallet wallet;

    public UserWallet(Long id, User users, Wallet wallet) {
        super(id);
        this.users = users;
        this.wallet = wallet;
    }
}
