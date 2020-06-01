package com.itheima.nacos;


import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import java.util.Properties;
import java.util.concurrent.Executor;

public class SimpleDemoMain {

    public static void main(String[] args) throws NacosException, InterruptedException {
        // 使用nacos client远程获取nacos服务上的配置信息
        // nacos server地址
        String serverAddr = "127.0.0.1:8848";
        // data Id
        String dataId = "nacos-simple-demo.yaml";
        // group
        String group = "DEFAULT_GROUP";
        //  namespace
        String namespace = "d98d1272-87b8-41de-bb65-0ea9dcf316c9";
        Properties properties = new Properties();
        properties.put("serverAddr", serverAddr);
//        properties.put("namespace", namespace);
        ConfigService configService = NacosFactory.createConfigService(properties);
        // 获取配置
        String content = configService.getConfig(dataId, group, 5000);
        System.out.println(content);

        configService.addListener(dataId, group, new Listener() {
            public Executor getExecutor() {
                return null;
            }

            public void receiveConfigInfo(String s) {
                System.out.println(s);
            }
        });

        while (true) {
            Thread.sleep(2000);
        }
    }
}
