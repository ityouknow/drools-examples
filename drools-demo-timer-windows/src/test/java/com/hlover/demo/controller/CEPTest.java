package com.hlover.demo.controller;

import com.hlover.demo.utils.KieSessionUtils;
import org.junit.Test;
import org.kie.api.runtime.KieSession;

public class CEPTest {

    @Test
    public void test01() throws Exception{
        KieSession kieSession= KieSessionUtils.newKieSession("rules/test01.drl");
        kieSession.fireUntilHalt();
    }

    /**
     * 主要测试：窗口
     * @throws Exception
     */
    @Test
    public void test02() throws Exception{
        KieSession kieSession= KieSessionUtils.newKieSession2("rules/test02.drl");
        kieSession.insert(2d);
        kieSession.insert(3d);
        Thread.sleep(20000);
        kieSession.insert(5d);
        kieSession.fireAllRules();
    }
}
