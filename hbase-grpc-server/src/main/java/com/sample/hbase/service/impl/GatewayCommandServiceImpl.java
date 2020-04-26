package com.sample.hbase.service.impl;

import com.incarcloud.proto.gateway.Gateway;
import com.incarcloud.proto.gateway.GatewayCommandServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 网关发送命令服务
 *
 * @author Aaric, created on 2020-04-26T14:20.
 * @version 1.1.0-SNAPSHOT
 */
@Slf4j
@Service
public class GatewayCommandServiceImpl extends GatewayCommandServiceGrpc.GatewayCommandServiceImplBase {

    /**
     * 下发命令
     */
    @Override
    public void executeCommand(Gateway.GatewayCommandParam request, StreamObserver<Gateway.GatewayCommandData> responseObserver) {
        // TODO 下发T-Box下行命令
        log.info("DeviceId: {}, CommandString: {}", request.getDeviceId(), request.getCommandString());

        // 执行结果-Mock
        Gateway.GatewayCommandData data = Gateway.GatewayCommandData.newBuilder()
                .setStatus(1)
                .setMessage("SUCCESS")
                .build();

        // 返回请求结果
        responseObserver.onNext(data);
        responseObserver.onCompleted();
    }
}
