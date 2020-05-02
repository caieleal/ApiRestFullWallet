package com.wallet.convert;

import com.wallet.dto.WalletDTO;
import com.wallet.entity.Wallet;

public class ConvertWallets {

    protected Wallet convertDtoToEntity(WalletDTO walletDTO){
        Wallet wallet = new Wallet();
        wallet.setId(walletDTO.getId());
        wallet.setName(walletDTO.getName());
        wallet.setValue(walletDTO.getValue());
        return wallet;
    }
    protected WalletDTO convertEntityToDto(Wallet wallet){
        WalletDTO walletDTO = new WalletDTO();
        walletDTO.setId(wallet.getId());
        walletDTO.setName(wallet.getName());
        walletDTO.setValue(wallet.getValue());
        return walletDTO;
    }
}
