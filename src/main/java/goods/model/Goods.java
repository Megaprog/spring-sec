package goods.model;

import javax.persistence.*;

@Entity
@Table(name = "goods")
@NamedQueries({
        @NamedQuery(name = "GoodsAll", query = "select g from Goods g order by g.name"),
        @NamedQuery(name = "GoodsByName", query = "select g from Goods g where g.name like :name order by g.name"),
        @NamedQuery(name = "GoodsByNamePrice", query = "select g from Goods g where g.name = :name and g.price = :price")
})
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private long price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Goods goods = (Goods) o;

        if (price != goods.price) return false;
        if (!name.equals(goods.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (int) (price ^ (price >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
