package com.wallet.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.dto.WalletDTO;
import com.wallet.entity.Wallet;
import com.wallet.service.WalletService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class WalletControllerTest {

    private static final Long ID = 1L;
    private static final String NAME = "ebk";
    private static final Integer VALUE = 2000;
    private static final String URL = "/wallet";

    @MockBean
    private WalletService service;

    @Autowired
    private MockMvc mockMvc;

    public Wallet getMockWallet() {
        Wallet wallet = new Wallet();
        wallet.setId(ID);
        wallet.setName(NAME);
        wallet.setValue(BigDecimal.valueOf(VALUE));
        return wallet;
    }

    public String getJsonPayLoad(Long id, String name, Integer value) throws JsonProcessingException {
        WalletDTO dto = new WalletDTO();
        dto.setId(id);
        dto.setName(name);
        dto.setValue(BigDecimal.valueOf(value));

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(dto);
    }

    @Test
    public void testSave() throws Exception {
        BDDMockito.given(service.save(Mockito.any(Wallet.class))).willReturn(getMockWallet());
        mockMvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayLoad(ID, NAME, VALUE))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.id").value(ID))
                .andExpect(jsonPath("$.data.name").value(NAME))
                .andExpect(jsonPath("$.data.value").value(VALUE));

    }

}
