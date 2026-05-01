package com.gildedrose;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
@SpringBootTest
@AutoConfigureMockMvc
public class GildedRoseControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnItemsOnGet() throws Exception {
        mockMvc.perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThan(0))))
                .andExpect(jsonPath("$[0].name", is("+5 Dexterity Vest")));
    }
    @Test
    void shouldAddNewItemViaPost() throws Exception {
        Item newItem = new Item("New Item Test", 10, 10);
        String json = objectMapper.writeValueAsString(newItem);

        mockMvc.perform(post("/item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(content().string("Ok"));

        mockMvc.perform(get("/items"))
                .andExpect(jsonPath("$[?(@.name == 'New Item Test')]").exists());
    }
    @Test
    void shouldUpdateQualityViaPost() throws Exception {

        Item[] testItems = { new Item("Aged Brie", 2, 0) };
        mockMvc.perform(put("/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testItems)));


        mockMvc.perform(post("/items/update"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].quality", is(1)))
                .andExpect(jsonPath("$[0].sellIn", is(1)));
    }
}
