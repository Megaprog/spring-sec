package goods.service;

import goods.AbstractIT;
import goods.model.Goods;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static org.junit.Assert.*;

@Transactional
public class GoodsServiceIT extends AbstractIT {

    @Autowired
    private GoodsService goodsService;

    @Test
    public void testAdd() throws Exception {
        final Goods goods = createGoods();

        assertTrue(goodsService.add(goods));
        assertEquals(goods, entityManager.find(Goods.class, goods.getId()));

        final Goods duplicateGoods = createGoods();
        assertFalse(goodsService.add(duplicateGoods));
    }

    @Test
    public void testUpdate() throws Exception {
        final Goods goods = createGoods();
        entityManager.persist(goods);

        final Goods updatedGoods = new Goods();
        updatedGoods.setId(goods.getId());
        updatedGoods.setName(goods.getName());
        updatedGoods.setPrice(120L);
        assertTrue(goodsService.update(updatedGoods));
        assertEquals(updatedGoods, entityManager.find(Goods.class, goods.getId()));

        final Goods duplicateGoods = new Goods();
        duplicateGoods.setId(goods.getId());
        duplicateGoods.setName(goods.getName());
        duplicateGoods.setPrice(goods.getPrice());
        assertFalse(goodsService.add(duplicateGoods));
    }

    @Test
    public void testDelete() throws Exception {
        final Goods goods = createGoods();
        entityManager.persist(goods);

        assertTrue(goodsService.delete(goods));
        assertFalse(goodsService.delete(goods));
    }

    @Test
    public void testGetGoods() throws Exception {
        final Goods goods1 = createGoods();
        final Goods goods2 = new Goods();
        goods2.setName("Хрен");
        goods2.setPrice(80L);

        entityManager.persist(goods2);
        entityManager.persist(goods1);

        assertEquals(Arrays.asList(goods1, goods2), goodsService.getGoods(""));
        assertEquals(Arrays.asList(goods2), goodsService.getGoods("Х"));
        assertEquals(Arrays.asList(goods1), goodsService.getGoods("Р"));
    }

    protected Goods createGoods() {
        final Goods goods = new Goods();
        goods.setName("Редька");
        goods.setPrice(100L);
        return goods;
    }
}