package webservice;

import javax.jws.WebMethod;

/**
 * Created by dx on 2018/8/8.
 * WebService接口
 *
 * @author dx
 */

@javax.jws.WebService
public interface WebService {
    /**
     * @param name 打招呼对象
     * @return hello+name
     */
    @WebMethod
    StringBuffer sayHello(String name);
}
