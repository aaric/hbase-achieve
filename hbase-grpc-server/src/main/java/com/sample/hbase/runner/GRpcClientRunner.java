package com.sample.hbase.runner;

import com.incarcloud.proto.register.PvoServerServiceGrpc;
import com.incarcloud.proto.register.Register;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

/**
 * 在登录注册服务中注册自己
 *
 * @author Aaric, created on 2020-04-20T17:22.
 * @version 1.0-SNAPSHOT
 */
@Slf4j
@Order(2)
@Component
public class GRpcClientRunner implements CommandLineRunner {

    /**
     * 注册登记服务器主机名
     */
    private String hostname = "10.0.11.25";

    /**
     * 注册登记服务器端口
     */
    private int port = 40000;

    @Override
    public void run(String... args) throws Exception {
        // 创建与注册登记服务的通道
        ManagedChannel channel = ManagedChannelBuilder.forAddress(hostname, port)
                .usePlaintext()
                .build();

        // 注册自己的服务信息
        PvoServerServiceGrpc.PvoServerServiceBlockingStub stub = PvoServerServiceGrpc.newBlockingStub(channel);
        Register.PvoRegisterParam request = Register.PvoRegisterParam.newBuilder()
                //.setHostname(host.getHostAddress())
                .setHostname("127.0.0.1")
                .setPort(40010)
                .build();

        // TODO 根据注册登记分配的vin集合，监控数据流
        Register.PvoRegisterDataList pvoRegisterDataList = stub.registerServer(request);
        log.info(pvoRegisterDataList.toString());

        // 关闭客户端连接
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }
}
