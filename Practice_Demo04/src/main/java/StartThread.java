import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by dx on 2018/8/10.
 *
 * @author dx
 */
public class StartThread {
    public static void main(String[] args) {
        StoreHouse storeHouse = new StoreHouse();

         //设置初始货物量以及最大容量
        storeHouse.setMax(1000);
        storeHouse.setGoods(0);
        //实例化生产者消费者
        Producer producer1 = new Producer(storeHouse);
        Producer producer2 = new Producer(storeHouse);
        Consumer consumer1 = new Consumer(storeHouse);
        Consumer consumer2 = new Consumer(storeHouse);
        Consumer consumer3 = new Consumer(storeHouse);


        //执行 Callable 方式，需要 FutureTask 实现类的支持，用于接收运算结果
        FutureTask protask1 = new FutureTask(producer1);
        FutureTask protask2 = new FutureTask(producer2);
        FutureTask contask1 = new FutureTask(consumer1);
        FutureTask contask2 = new FutureTask(consumer2);
        FutureTask contask3 = new FutureTask(consumer3);

        //设置单次生产，消费货物数量
        producer1.setGoods(10);
        producer2.setGoods(10);
        consumer1.setGoods(5);
        consumer2.setGoods(5);
        consumer3.setGoods(5);

        //启动线程
        new Thread(protask1).start();
        new Thread(protask2).start();
        new Thread(contask1).start();
        new Thread(contask2).start();
        new Thread(contask3).start();

        try {
            //通过返回的结果捕获异常信息
            protask1.get();
            protask2.get();
            contask1.get();
            contask2.get();
            contask3.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
