package webservice;

/**
 * Created by dx on 2018/8/8.
 * WebService实现类
 * @author dx
 */
@javax.jws.WebService
public class WebServiceImpl implements WebService {
    @Override
    public StringBuffer sayHello(String name) {
        System.out.println("启动");
        StringBuffer say = new StringBuffer("hello "+name);
        return say;
    }
}
