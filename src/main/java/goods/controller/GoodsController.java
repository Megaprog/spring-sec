package goods.controller;

import goods.model.Goods;
import goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    protected GoodsService goodsService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Goods> getGoods(@RequestParam(required = false) String search) {
        return goodsService.getGoods(search);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ExtResult setGoods(@RequestBody Goods goods) {
        return new ExtResult(goodsService.add(goods), goods);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public String deleteGoods(@RequestBody Goods goods) {
        goodsService.delete(goods);
        return "delete";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ExtResult updateGoods(@RequestBody Goods goods) {
        return new ExtResult(goodsService.update(goods), goods);
    }

    public static class ExtResult {
        private boolean success;
        private Goods data;


        public ExtResult(boolean success, Goods data) {
            this.success = success;
            this.data = data;
        }

        public ExtResult() {
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public Goods getData() {
            return data;
        }

        public void setData(Goods data) {
            this.data = data;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ExtResult extResult = (ExtResult) o;

            if (success != extResult.success) return false;
            if (!data.equals(extResult.data)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = (success ? 1 : 0);
            result = 31 * result + data.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "ExtResult{" +
                    "success=" + success +
                    ", data=" + data +
                    '}';
        }
    }
}
