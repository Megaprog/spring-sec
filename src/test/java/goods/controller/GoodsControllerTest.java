package goods.controller;

import goods.AbstractMvcTest;
import goods.model.Goods;
import goods.service.GoodsService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class GoodsControllerTest extends AbstractMvcTest {

    private static final String URL = "/goods";

    @Autowired
    GoodsService goodsService;

    @Before
    public void setUp() throws Exception {
        reset(goodsService);
    }

    @Test
    public void testGetGoods_NullSearch() throws Exception {
        final Goods goods = createGoods();
        when(goodsService.getGoods(null)).thenReturn(Collections.singletonList(goods));

        mockMvc.perform(MockMvcRequestBuilders.get(URL))
                .andExpect(content().string(objectMapper.writeValueAsString(Collections.singleton(goods))));
    }

    @Test
    public void testGetGoods_Search() throws Exception {
        final Goods goods = createGoods();
        when(goodsService.getGoods("Р")).thenReturn(Collections.singletonList(goods));

        mockMvc.perform(MockMvcRequestBuilders.get(URL).param("search", "Р"))
                .andExpect(content().string(objectMapper.writeValueAsString(Collections.singleton(goods))));
    }

    @Test
    public void testSetGoods() throws Exception {
        final Goods goods = createGoods();
        when(goodsService.add(goods)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post(URL).contentType(APPLICATION_JSON).content(objectMapper.writeValueAsString(goods)))
                .andExpect(content().string(objectMapper.writeValueAsString(new GoodsController.ExtResult(true, goods))));
    }

    @Test
    public void testDeleteGoods() throws Exception {
        final Goods goods = createGoods();

        mockMvc.perform(MockMvcRequestBuilders.delete(URL + "/" + goods.getId()).contentType(APPLICATION_JSON).content(objectMapper.writeValueAsString(goods)))
                .andExpect(content().string("delete"));
    }

    @Test
    public void testUpdateGoods() throws Exception {
        final Goods goods = createGoods();
        when(goodsService.update(goods)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.put(URL + "/" + goods.getId()).contentType(APPLICATION_JSON).content(objectMapper.writeValueAsString(goods)))
                .andExpect(content().string(objectMapper.writeValueAsString(new GoodsController.ExtResult(true, goods))));
    }

    protected Goods createGoods() {
        final Goods goods = new Goods();
        goods.setId(1L);
        goods.setName("Редька");
        goods.setPrice(100L);
        return goods;
    }
}