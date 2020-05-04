package com.wallet.repository;

import com.wallet.entity.Wallet;
import com.wallet.entity.WalletItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class WalletItemsRepositoryTest {

    private static final Date DATE = new Date();
    private static final String TYPE = "EN";
    private static final String DESCRIPTION = "CONTA DE LUZ";
    private static final BigDecimal VALUE = BigDecimal.valueOf(65);

    @Autowired
    private WalletItemRepository repository;

    @Autowired
    private WalletRepository walletRepository;

    @Test
    public void testSave(){
        Wallet wallet = new Wallet();
        wallet.setName("Carteira 1");
        wallet.setValue(BigDecimal.valueOf(500));
        walletRepository.save(wallet);

        WalletItem walletItem = new WalletItem(wallet,DATE, TYPE, DESCRIPTION, VALUE);
        WalletItem response = repository.save(walletItem);
        assertNotNull(response);
        assertEquals(response.getDescription(), DESCRIPTION);
        assertEquals(response.getType(), TYPE);
        assertEquals(response.getWallet().getId(), wallet.getId());

    }

}
