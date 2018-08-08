package webservice;

import javax.jws.*;
import javax.xml.ws.Endpoint;

/**
 * Created by dx on 2018/8/8.
 * WebService发布类
 * @author  dx
 */
public class WebServicePublish {

    public static void main(String[] args) {
        /**
         * 配置发布地址
         */
        String address = "http://localhost:8888/WebService";

        Endpoint.publish(address,new WebServiceImpl());
        System.out.println("WebService已发布");
    }

}
