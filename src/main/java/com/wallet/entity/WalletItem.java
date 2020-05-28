package com.wallet.entity;

import com.wallet.util.enums.TypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "wallet_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletItem extends AbstractEntity<Long> {

    @JoinColumn(name = "wallet", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Wallet wallet;

    @NotNull
    private Date date;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TypeEnum type;

    @NotNull
    private String description;

    @NotNull
    private BigDecimal value;



    public WalletItem(Long id, @NotNull Wallet wallet, @NotNull Date date, @NotNull TypeEnum type, @NotNull String description, @NotNull BigDecimal value) {
        this.setId(id);
        this.wallet = wallet;
        this.date = date;
        this.type = type;
        this.description = description;
        this.value = value;
    }
}
