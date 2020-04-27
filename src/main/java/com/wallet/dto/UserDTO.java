package com.wallet.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class UserDTO {

    private Long id;
    @Email(message = "Email inválido")
    private String email;

    @Length(min = 3, max = 50, message = "O nome deve conter entre 3 e 50 caracteres")
    private String name;

    @NotNull
    @Length(min = 3, max = 6, message = "A senha deve conter entre 3 e 6 caracteres")
    private String password;
}
