package com.wallet.convert;

import com.wallet.dto.UserWalletDTO;
import com.wallet.entity.User;
import com.wallet.entity.UserWallet;
import com.wallet.entity.Wallet;

public class ConvertUsersWallets {

    protected UserWallet convertDtoToEntity(UserWalletDTO dto){
        UserWallet userWallet = new UserWallet();
        User user = new User();
        user.setId(dto.getUsers());

        Wallet wallet = new Wallet();
        wallet.setId(dto.getWallet());

        userWallet.setId(dto.getId());
        userWallet.setUsers(user);
        userWallet.setWallet(wallet);
        return userWallet;
    }
    protected UserWalletDTO convertEntityToDto(UserWallet userWallet){
        UserWalletDTO userWalletDTO = new UserWalletDTO();
        userWalletDTO.setId(userWallet.getId());
        userWalletDTO.setWallet(userWallet.getWallet().getId());
        userWalletDTO.setUsers(userWallet.getUsers().getId());
        return userWalletDTO;
    }
}
