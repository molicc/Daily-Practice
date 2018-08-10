import java.util.concurrent.Callable;

/**
 * Created by dx on 2018/8/10.
 *
 * @author dx
 */
public class Consumer implements Callable {
    /**
     * 每次消费的货物数
     * 声明公共资源对象
     */
    private Integer goods;
    private StoreHouse storeHouse;

    /**
     * 获取实例化的公共资源对象
     * @param storeHouse 实例化的公共资源对象
     */
    Consumer(StoreHouse storeHouse) {
        this.storeHouse = storeHouse;
    }

    /**
     * 获取每次消费产品数量
     * @param goods 产品数量
     */
    void setGoods(Integer goods) {
        this.goods = goods;
    }

    @Override
    public Object call() throws Exception {
        //循环调用
        while (goods > 0) {
            storeHouse.consume(goods);
        }
        return null;
    }
}
