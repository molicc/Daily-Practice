import java.util.concurrent.Callable;

/**
 * Created by dx on 2018/8/10.
 *
 * @author dx
 */
public class Producer implements Callable {
    /**
     * 每次生产货物量
     * 声明公共资源对象
     */
    private StoreHouse storeHouse;
    private Integer goods;

    /**
     * 获取实例化的公共资源对象
     * @param storeHouse 实例化的公共资源对象
     */
    Producer(StoreHouse storeHouse) {
        this.storeHouse = storeHouse;
    }

    /**
     * 获取每次生产产品数量
     * @param goods 产品数量
     */
    void setGoods(Integer goods) {
        this.goods = goods;
    }

    /**
     *
     * @return 返回线程中异常信息
     * @throws Exception 线程中异常异常
     */
    @Override
    public Object call() throws Exception {
        Integer max = storeHouse.getMax();
        //循环调用
        while (goods < max) {
            storeHouse.produce(goods);
        }
        return null;
    }
}
