package com.wallet.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.dto.UserWalletDTO;
import com.wallet.entity.User;
import com.wallet.entity.UserWallet;
import com.wallet.entity.Wallet;
import com.wallet.service.UserWalletService;
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


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserWalletControllerTest {

    private static final Long ID = 1L;
    private static final Long USER = 2L;
    private static final Long WALLET = 3L;
    private static final String URL = "/user-wallet";


    @MockBean
    private UserWalletService service;

    @Autowired
    private MockMvc mvc;

    @Test
    public void testSaveUserWallet() throws Exception {
        BDDMockito.given(service.save(Mockito.any(UserWallet.class))).willReturn(getMockUserWallet());

        mvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayLoad(ID, WALLET, USER))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.id").value(ID))
                .andExpect(jsonPath("$.data.users").value(USER))
                .andExpect(jsonPath("$.data.wallet").value(WALLET));

    }

    @Test
    public void testInvalidSaveUserWallet() throws Exception {
        BDDMockito.given(service.save(Mockito.any(UserWallet.class))).willReturn(getMockUserWallet());

        mvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayLoad(ID, null, null))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]").value("Informe o Id do usuário."))
                .andExpect(jsonPath("$.errors[1]").value("Informe o Id da carteira."));

    }

    private UserWallet getMockUserWallet() {
        User user = new User();
        user.setId(USER);
        Wallet wallet = new Wallet();
        wallet.setId(WALLET);

        UserWallet uw = new UserWallet(1L, user, wallet);
        return uw;

    }

    private String getJsonPayLoad(Long id, Long wallet, Long user) throws JsonProcessingException {
        UserWalletDTO dto = new UserWalletDTO();
        dto.setId(id);
        dto.setWallet(wallet);
        dto.setUsers(user);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(dto);
    }


}
