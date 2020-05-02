package com.wallet.controller;

import com.wallet.convert.ConvertWallets;
import com.wallet.dto.WalletDTO;
import com.wallet.entity.Wallet;
import com.wallet.response.Response;
import com.wallet.service.WalletService;
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
@RequestMapping("wallet")
public class WalletController extends ConvertWallets {

    @Autowired
    private WalletService walletService;


    @PostMapping
    public ResponseEntity<Response<WalletDTO>> create(@Valid @RequestBody WalletDTO walletDTO, BindingResult bindingResult) {

        Response<WalletDTO> response = new Response<>();
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);

        }
        Wallet wallet = walletService.save(this.convertDtoToEntity(walletDTO));
        response.setData(this.convertEntityToDto(wallet));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
}
