package com.nickleback.quartettBackend.controller;

import com.nickleback.quartettBackend.core.BaseIT;
import com.nickleback.quartettBackend.core.buisness.MvcUtils;
import com.nickleback.quartettBackend.domain.CardDeck;
import com.nickleback.quartettBackend.dto.GameDto;
import com.nickleback.quartettBackend.testDataProvider.CardDeckTestDataProvider;
import com.nickleback.quartettBackend.testDataProvider.GameTestDataProvider;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.assertEquals;

public class GameRestControllerIT extends BaseIT {

    @Autowired
    private GameTestDataProvider gameTestDataProvider;

    @Autowired
    private CardDeckTestDataProvider cardDeckTestDataProvider;

    @Test
    public void testCreateGame() throws Exception{
        CardDeck cardDeck = cardDeckTestDataProvider.create();

        mockMvc.perform(post("/api/startGame/with/" + cardDeck.getId()))
                .andExpect(status().isCreated());


    }



}
