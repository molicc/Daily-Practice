/**
 * Created by dx on 2018/8/10.
 *
 * @author dx
 */
class StoreHouse {
    /**
     * 仓库容量
     * 当前货物数量
     */
    private Integer max;
    private Integer goods;


    void setMax(Integer max) {
        this.max = max;
    }

    void setGoods(Integer goods) {
        this.goods = goods;
    }

    Integer getMax() {
        return max;
    }

    /**
     * 生产货物
     */
    synchronized void produce(Integer num) {
        //当超出容量时，等待挂起
        while ((goods + num) > max) {
            System.out.println("--------------超出容量--------------");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        goods += num;
        System.out.println("正在生产--" + goods);
        //释放
        notifyAll();

    }

    /**
     * 消费货物
     */
    synchronized void consume(Integer num) {
        while ((goods - num) < 0) {
            System.out.println("--------------库存不足--------------");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        goods -= num;
        System.out.println("正在消费--" + goods);
        notifyAll();

    }
}
