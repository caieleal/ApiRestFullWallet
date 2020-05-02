package com.wallet.repository;

import com.wallet.entity.Wallet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class RepositoryWalletTest {

    private static final Long ID = 1L;
    private static final String NAME = "ebk";
    private static final Integer VALUE = 1500;


    @Autowired
    private WalletRepository repository;

    @Before
    public void init(){
        Wallet wallet = new Wallet();
        wallet.setId(ID);
        wallet.setName(NAME);
        wallet.setValue(BigDecimal.valueOf(VALUE));
        repository.save(wallet);
    }
    @After
    public void finish(){
        repository.deleteAll();
    }
    @Test
    public void testSave(){
        Wallet wallet = new Wallet();
        wallet.setId(ID);
        wallet.setValue(BigDecimal.valueOf(VALUE));
        wallet.setName(NAME);

        Wallet response = repository.save(wallet);
        assertNotNull(response);
    }
}
