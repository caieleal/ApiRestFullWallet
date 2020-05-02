package com.wallet.service;

import com.wallet.entity.UserWallet;
import com.wallet.repository.UserWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserWalletServiceImpl implements UserWalletService {

    @Autowired
    private UserWalletRepository userWalletRepository;

    @Override
    public UserWallet save(UserWallet userWallet) {
        return userWalletRepository.save(userWallet);
    }
}
