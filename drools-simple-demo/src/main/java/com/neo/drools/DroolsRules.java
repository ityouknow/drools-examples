package com.neo.drools;

import com.neo.drools.entity.Order;
import com.neo.drools.entity.User;
import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderErrors;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.definition.KnowledgePackage;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class DroolsRules {
  
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
    public static void main(String[] args) throws Exception {  
        KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        builder.add(ResourceFactory.newClassPathResource("rules/point-rules.drl"), ResourceType.DRL);
  
        if (builder.hasErrors()) {  
            System.out.println("规则中存在错误，错误消息如下：");  
            KnowledgeBuilderErrors kbuidlerErrors = builder.getErrors();
            for (Iterator iter = kbuidlerErrors.iterator(); iter.hasNext();) {
                System.out.println(iter.next());  
            }  
            return;  
        }  
  
        Collection<KnowledgePackage> packages = builder.getKnowledgePackages();
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(packages);  
  
        StatefulKnowledgeSession session = kbase.newStatefulKnowledgeSession();
  
        List<Order> orderList = getInitData();
  
        for (int i = 0; i < orderList.size(); i++) {  
            Order o = orderList.get(i);  
            session.insert(o);  
            session.fireAllRules();  
            // 执行完规则后, 执行相关的逻辑  
            addScore(o);  
        }  
  
        session.dispose();  
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