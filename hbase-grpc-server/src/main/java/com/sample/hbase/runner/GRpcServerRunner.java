package com.sample.hbase.runner;

import com.sample.hbase.service.impl.GatewayCommandServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * gRPC服务启动类
 *
 * @author Aaric, created on 2020-04-26T14:18.
 * @version 1.1.0-SNAPSHOT
 */
@Slf4j
@Order(3)
@Component
public class GRpcServerRunner implements CommandLineRunner {

    /**
     * 暴露服务端口
     */
    private int serverPort = 40020;

    /**
     * 网关发送命令服务
     */
    @Autowired
    private GatewayCommandServiceImpl gatewayCommandService;

    @Override
    public void run(String... args) throws Exception {
        // 启动服务
        log.info("starting...");
        Server server = ServerBuilder.forPort(serverPort)
                .addService(gatewayCommandService)
                .build()
                .start();

        // 关闭服务
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("stopping...");
            if (null != server) {
                server.shutdown();
            }
            log.info("stopped.");
        }));

        // 等待终止...
        log.info("started.");
        server.awaitTermination();
    }
}
