package com.neo.drools;

import com.neo.drools.entity.Order;
import com.neo.drools.entity.User;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DroolsScoreExample {


    /**
     * 计算额外积分金额 规则如下: 订单原价金额
     * 100以下, 不加分
     * 100-500 加100分
     * 500-1000 加500分
     * 1000 以上 加1000分
     *
     * @param args
     * @throws Exception
     */
    public static final void main(final String[] args) throws Exception{
        // KieServices is the factory for all KIE services
        KieServices ks = KieServices.Factory.get();

        // From the kie services, a container is created from the classpath
        KieContainer kc = ks.getKieClasspathContainer();

        execute( kc );
    }


    public static void execute( KieContainer kc ) throws Exception{
        // From the container, a session is created based on
        // its definition and configuration in the META-INF/kmodule.xml file
        KieSession ksession = kc.newKieSession("point-rulesKS");

        List<Order> orderList = getInitData();

        for (int i = 0; i < orderList.size(); i++) {
            Order o = orderList.get(i);
            ksession.insert(o);
            ksession.fireAllRules();
            // 执行完规则后, 执行相关的逻辑
            addScore(o);
        }

        ksession.dispose();

    }

  
    private static void addScore(Order o){  
        System.out.println("用户" + o.getUser().getName() + "享受额外增加积分: " + o.getScore());  
    }  
      
    private static List<Order> getInitData() throws Exception {  
        List<Order> orderList = new ArrayList<Order>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        {  
            Order order = new Order();  
            order.setAmout(80);  
            order.setBookingDate(df.parse("2015-07-01"));  
            User user = new User();  
            user.setLevel(1);  
            user.setName("Name1");  
            order.setUser(user);
            order.setScore(111);
            orderList.add(order);  
        }  
        {  
            Order order = new Order();  
            order.setAmout(200);  
            order.setBookingDate(df.parse("2015-07-02"));  
            User user = new User();  
            user.setLevel(2);  
            user.setName("Name2");  
            order.setUser(user);  
            orderList.add(order);  
        }  
        {  
            Order order = new Order();  
            order.setAmout(800);  
            order.setBookingDate(df.parse("2015-07-03"));  
            User user = new User();  
            user.setLevel(3);  
            user.setName("Name3");  
            order.setUser(user);  
            orderList.add(order);  
        }  
        {  
            Order order = new Order();  
            order.setAmout(1500);  
            order.setBookingDate(df.parse("2015-07-04"));  
            User user = new User();  
            user.setLevel(4);  
            user.setName("Name4");  
            order.setUser(user);  
            orderList.add(order);  
        }  
        return orderList;  
    }  
}  