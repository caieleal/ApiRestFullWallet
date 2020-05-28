package com.wallet.entity;

import lombok.Data;

import javax.persistence.Entity;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Data
public class Wallet extends AbstractEntity<Long> {


    @NotNull
    private String name;

    @NotNull
    private BigDecimal value;


    }
