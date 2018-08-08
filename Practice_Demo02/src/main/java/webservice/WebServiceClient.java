package webservice;

import webservice_wsdl.WebServiceImplService;

/**
 * Created by dx on 2018/8/8.
 * @author dx
 */
public class WebServiceClient {
    public static void main(String[] args) {
        WebServiceImplService factory = new WebServiceImplService();
        WebServiceImpl webService = (WebServiceImpl) factory.getWebServiceImplPort();
        StringBuffer  sbu = webService.sayHello("dx");
        System.out.println("返回结果为："+sbu);
    }
}
