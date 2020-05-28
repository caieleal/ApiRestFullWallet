package com.wallet.repository;

import com.wallet.entity.Wallet;
import com.wallet.entity.WalletItem;
import com.wallet.util.enums.TypeEnum;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class WalletItemRepositoryTest {

    private static final Long ID = 1L;
    private static final Date DATE = new Date();
    private static final TypeEnum TYPE = TypeEnum.EN;
    private static final String DESCRIPTION = "Description";
    private static final BigDecimal VALUE = BigDecimal.valueOf(1500.00);
    private Long walletItemCreate = null;
    private Long walletCreate = null;

    @Autowired
    private WalletItemRepository walletItemRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Before
    public void init(){
        Wallet wallet = new Wallet();
        wallet.setName("setUpSaveFirst");
        wallet.setValue(BigDecimal.valueOf(500));
        walletRepository.save(wallet);

        WalletItem walletItem = new WalletItem(null, wallet, DATE, TYPE, DESCRIPTION, VALUE);
        walletItemRepository.save(walletItem);

        walletItemCreate = walletItem.getId();
        walletCreate = wallet.getId();
    }

    @After
    public void finish(){
        walletItemRepository.deleteAll();
        walletRepository.deleteAll();
    }

    @Test
    public void testSaveWalletItem() {
        Wallet wallet = new Wallet();
        wallet.setName("walletTestItem");
        wallet.setValue(BigDecimal.valueOf(1800));
        walletRepository.save(wallet);

        WalletItem walletItem = new WalletItem(ID, wallet, DATE, TYPE, DESCRIPTION, VALUE);
        WalletItem response = walletItemRepository.save(walletItem);

        assertNotNull(response);
        assertEquals(response.getDescription(), DESCRIPTION);
        assertEquals(response.getType(), TYPE);
        assertEquals(response.getValue(), VALUE);
        assertEquals(response.getWallet().getId(), wallet.getId());

    }
    @Test(expected = ConstraintViolationException.class)
    public void testInvalidSave(){
        WalletItem walletItem = new WalletItem(null, null, DATE, null, DESCRIPTION, null);
        walletItemRepository.save(walletItem);
    }
    @Test
    public void testUpdateWalletItem(){
        Optional<WalletItem> walletItem = walletItemRepository.findById(walletItemCreate);

        String newDescription = "New Description Test";

        WalletItem walletItemUp = walletItem.get();

        walletItemUp.setDescription(newDescription);

        walletItemRepository.save(walletItemUp);

        Optional<WalletItem> walletItemUpdate = walletItemRepository.findById(walletItemCreate);

        assertEquals(newDescription, walletItemUpdate.get().getDescription());

    }
    @Test
    public void testDeleWalletItem(){
        Optional<Wallet> wallet = walletRepository.findById(walletCreate);
        WalletItem walletItem = new WalletItem(null, wallet.get(), DATE, TYPE, DESCRIPTION, VALUE);
        walletItemRepository.save(walletItem);
        walletItemRepository.deleteById(walletItem.getId());

        Optional<WalletItem> response = walletItemRepository.findById(walletItem.getId());
        assertFalse(response.isPresent());
    }

}
