package com.wallet.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class WalletItemDTO {

    private Long id;

    @NotNull(message = "Insira o id da carteira.")
    private Long Wallet;

    @NotNull(message = "Insira uma data.")
    private Date date;

    @NotNull(message = "Escolha um tipo.")
    @Pattern(regexp = "^(ENTRADA||SAÍDA$)", message = "Escolha apenas ENTRADA ou SAÍDA.")
    private String type;

    @NotNull(message = "Insira uma descrição.")
    @Length(min = 5, message = "Insira uma descrição com no mínimo 5 caractertes.")
    private String description;

    @NotNull(message = "Informe um valor.")
    private BigDecimal value;
}
