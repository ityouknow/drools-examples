package com.hlover.demo.utils;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.builder.model.KieBaseModel;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.conf.EqualityBehaviorOption;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
public class KieSessionUtils {

    private KieSessionUtils() {

    }

    /**
     * @description TODO (快速新建KieSession)
     * @param classPath 绝对路径
     * @return KieSession 有状态
     */
    public static KieSession newKieSession(String classPath) throws Exception {
        KieSession kieSession = getKieBase(classPath).newKieSession();
        kieSession.addEventListener(new DebugRuleRuntimeEventListener());
        return kieSession;

    }

    public static KieSession newKieSession2(String classPath) throws Exception {
        KieSession kieSession = getKieBaseUseStream(classPath).newKieSession();
        kieSession.addEventListener(new DebugRuleRuntimeEventListener());
        return kieSession;

    }

    /**
     * @description TODO (快速新建StatelessKieSession)
     * @param classPath 绝对路径
     * @return StatelessKieSession 无状态
     */
    public static StatelessKieSession newStatelessKieSession(String classPath) throws Exception {
        StatelessKieSession kiesession = getKieBase(classPath).newStatelessKieSession();
        return kiesession;

    }


    private static KieBase getKieBase(String classPath) throws Exception {
        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kfs = kieServices.newKieFileSystem();
        Resource resource = kieServices.getResources().newClassPathResource(classPath);
        resource.setResourceType(ResourceType.DRL);
        kfs.write(resource);
        KieBuilder kieBuilder = kieServices.newKieBuilder(kfs).buildAll();
        if (kieBuilder.getResults().getMessages(Message.Level.ERROR).size() > 0) {
            throw new Exception();
        }
        KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
        KieBase kBase = kieContainer.getKieBase();
        return kBase;
    }

    private static KieBase getKieBaseUseStream(String classPath) throws Exception {
        KieServices kieServices = KieServices.Factory.get();
        KieModuleModel kieModuleModel = kieServices.newKieModuleModel();
        KieBaseModel kieBaseModel1 = kieModuleModel.newKieBaseModel( "KBase1 ")
                .setDefault( true )
                .setEventProcessingMode( EventProcessingOption.STREAM );

        KieFileSystem kfs = kieServices.newKieFileSystem();
        kfs.writeKModuleXML(kieModuleModel.toXML());

        Resource resource = kieServices.getResources().newClassPathResource(classPath);
        resource.setResourceType(ResourceType.DRL);
        kfs.write(resource);
        KieBuilder kieBuilder = kieServices.newKieBuilder(kfs).buildAll();
        if (kieBuilder.getResults().getMessages(Message.Level.ERROR).size() > 0) {
            throw new Exception();
        }
        KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
        KieBase kBase = kieContainer.getKieBase();
        return kBase;
    }

}
