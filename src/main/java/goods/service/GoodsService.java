package goods.service;

import goods.model.Goods;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class GoodsService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public boolean add(Goods goods) {
        if (checkNotExists(goods)) {
            em.persist(goods);
            return true;}
        return false;
    }

    protected boolean checkNotExists(Goods goods) {
        return findByGoods(goods.getName(), goods.getPrice()).isEmpty();
    }

    protected List<Goods> findByGoods(String name, long price) {
        return em.createNamedQuery("GoodsByNamePrice", Goods.class).setParameter("name", name).setParameter("price", price).getResultList();
    }

    @Transactional
    public boolean update(Goods goods) {
        if (checkNotExists(goods)) {
            em.merge(goods);
            return true;
        }
        else {
            return false;
        }
    }

    @Transactional
    public boolean delete(Goods goods) {
        try {
            em.remove(em.getReference(Goods.class, goods.getId()));
            return true;
        }
        catch (EntityNotFoundException e) {
            return false;
        }
    }

    @Transactional(readOnly = true)
    public List<Goods> getGoods(String name) {
        if (name != null) {
            final String trimmedName = name.trim();
            if (!trimmedName.isEmpty()) {
                return em.createNamedQuery("GoodsByName", Goods.class).setParameter("name", trimmedName + "%").getResultList();
            }
        }

        return em.createNamedQuery("GoodsAll", Goods.class).getResultList();
    }
}
