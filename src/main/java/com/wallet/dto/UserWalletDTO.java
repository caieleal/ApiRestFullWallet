package com.wallet.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserWalletDTO {

    private Long id;
    @NotNull(message = "Informe o Id do usuário.")
    private Long users;
    @NotNull(message = "Informe o Id da carteira.")
    private Long wallet;
}
