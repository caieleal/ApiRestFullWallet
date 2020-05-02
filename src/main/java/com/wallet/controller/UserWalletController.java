package com.wallet.controller;

import com.wallet.convert.ConvertUsers;
import com.wallet.convert.ConvertUsersWallets;
import com.wallet.dto.UserWalletDTO;
import com.wallet.entity.UserWallet;
import com.wallet.response.Response;
import com.wallet.service.UserWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "user-wallet")
public class UserWalletController extends ConvertUsersWallets {

    @Autowired
    private UserWalletService service;

    @PostMapping
    private ResponseEntity<Response<UserWalletDTO>> create(@Valid @RequestBody UserWalletDTO dto, BindingResult result) {
        Response<UserWalletDTO> response = new Response<>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        UserWallet userWallet = service.save(this.convertDtoToEntity(dto));
        response.setData(this.convertEntityToDto(userWallet));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);


    }

}
