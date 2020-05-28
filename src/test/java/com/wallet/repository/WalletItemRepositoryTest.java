package com.wallet.repository;

import com.wallet.dto.WalletDTO;
import com.wallet.entity.Wallet;
import com.wallet.entity.WalletItem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class WalletItemRepositoryTest {

    private static final Long ID = 1L;
    private static final Date DATE = new Date();
    private static final String TYPE = "EN";
    private static final String DESCRIPTION = "Description";
    private static final BigDecimal VALUE = BigDecimal.valueOf(1500.00);

    @Autowired
    private WalletItemRepository repository;

    @Autowired
    private WalletRepository walletRepository;


    @Test
    public void testSaveWalletItem() {
        Wallet wallet = new Wallet();
        wallet.setName("walletTestItem");
        wallet.setValue(BigDecimal.valueOf(1800));
        walletRepository.save(wallet);

        WalletItem walletItem = new WalletItem(ID, wallet, DATE, TYPE, DESCRIPTION, VALUE);
        WalletItem response = repository.save(walletItem);

        assertNotNull(response);
        assertEquals(response.getDescription(), DESCRIPTION);
        assertEquals(response.getType(), TYPE);
        assertEquals(response.getValue(), VALUE);
        assertEquals(response.getWallet().getId(), wallet.getId());

    }

}
