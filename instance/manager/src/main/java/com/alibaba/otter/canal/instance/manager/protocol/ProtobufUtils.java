package com.alibaba.otter.canal.instance.manager.protocol;

import com.google.protobuf.ByteString;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * 一些工具方法，主要是用于初始化一些protobuf message instance
 * @author Wan Kaiming on 2017/1/3
 * @version 1.0
 */
public class ProtobufUtils {

    /**
     *
     * @return 默认的CanalModelMessage实例
     */
    public static CanalModelPacket.CanalModelMessage getDefaultCanalModeMessage(){

        //初始化默认的Canal实例,  这里一个packet中包含多个message类


        CanalModelPacket.CanalModelMessage defaultCanalModelMessage = CanalModelPacket.CanalModelMessage.newBuilder()
                .setCanalParameterMessage(getDefaultCanalParameterMessage())
                .setId(1L)
                .setName("example")
                .build();

        return defaultCanalModelMessage;
    }


    /**
     *
     * @return 默认配置的CanalParameterMessage实例，现在都写死了，主要用于自己来测试，后期有空改成从spring加载配置的方式
     */
    private static CanalParameterPacket.CanalParameterMessage getDefaultCanalParameterMessage(){
        //构造一个默认CanalParameterMessage用于生成CanalModelMessage实例,必须使用protobuf来构造

        //初始化BasicConfig
        CanalParameterPacket.BasicConfig basicConfig = CanalParameterPacket.BasicConfig.newBuilder()
                .setCanalId(1L)
                .setMetaMode(CanalParameterPacket.MetaMode.MetaMode_MEMORY)
                .setRunMode(CanalParameterPacket.RunMode.EMBEDDED)
                .setClusterMode(CanalParameterPacket.ClusterMode.STANDALONE).build();

        //初始化ZKConfig
        CanalParameterPacket.ZKConfig zkConfig = CanalParameterPacket.ZKConfig.newBuilder()
                .addAllZkClusters(Arrays.asList("10.45.10.33:2181/kaiming-canal"))         //注意这里可以加入一个List对象
                .setZkClusterId(1).build();


        //初始化 storage存储
        CanalParameterPacket.StorageConfig storageConfig = CanalParameterPacket.StorageConfig.newBuilder()
                .setTransactionSize(1024)
                .setStorageMode(CanalParameterPacket.StorageMode.StorageMode_MEMORY)
                .setStorageBatchMode(CanalParameterPacket.BatchMode.MEMSIZE)
                .setMemoryStorageBufferSize(16*1024)
                .setMemoryStorageBufferMemUnit(1024)
                .setStorageScavengeMode(CanalParameterPacket.StorageScavengeMode.ON_ACK)
                .build();

        //初始化replication配置
        CanalParameterPacket.ReplicationConfig replicationConfig = CanalParameterPacket.ReplicationConfig.newBuilder()
                .setSourcingType(CanalParameterPacket.SourcingType.MYSQL)
                .setHaMode(CanalParameterPacket.HAMode.HEARTBEAT)
                .build();

        //初始化网络连接参数
        CanalParameterPacket.NetConfig netConfig = CanalParameterPacket.NetConfig.newBuilder()
                .setPort(11111)
                .setDefaultConnectionTimeoutInSeconds(30)
                .setReceiveBufferSize(64*1024)
                .setSendBufferSize(64*1024)
                .build();


        //初始化编码信息
        CanalParameterPacket.CharsetConfig charsetConfig=CanalParameterPacket.CharsetConfig.newBuilder()
                .setConnectionCharset("UTF-8")
                .setConnectionCharsetNumber(ByteString.copyFrom(ByteBuffer.allocateDirect((byte)33)))
                .build();


        //初始化InetSocketAddressMessage
        CanalParameterPacket.InetSocketAddressMessage inetSocketAddressMessage = CanalParameterPacket.InetSocketAddressMessage.newBuilder()
                .setIPAddress("10.45.10.33")
                .setPort(3306).build();

        //初始化DB config
        CanalParameterPacket.DBConfig dbConfig= CanalParameterPacket.DBConfig.newBuilder()
                .addAllDbAddresses(Arrays.asList(inetSocketAddressMessage))
                .setDbUsername("canal")
                .setDbPassword("canal").build();




        //初始化binlog配置
        CanalParameterPacket.BinLogConnectConfig binLogConnectConfig = CanalParameterPacket.BinLogConnectConfig.newBuilder()
                .setFallbackIntervalInSeconds(60)
                .setIndexMode(CanalParameterPacket.IndexMode.IndexMode_MEMORY)
                .build();


        //初始化心跳配置
        CanalParameterPacket.HeartBeatConfig heartBeatConfig = CanalParameterPacket.HeartBeatConfig.newBuilder()
                .setDetectingEnable(true)
                .setHeartbeatHaEnable(false)
                .setDetectingIntervalInSeconds(3)
                .setDetectingTimeoutThresholdInSeconds(30)
                .setDetectingRetryTimes(3)
                .build();

        //初始化DDL支持配置
        CanalParameterPacket.DDLSupportConifg ddlSupportConifg = CanalParameterPacket.DDLSupportConifg.newBuilder()
                .setDdlIsolation(false)
                .setFilterTableError(false)
                .build();


        CanalParameterPacket.CanalParameterMessage canalParameterMessage = CanalParameterPacket.CanalParameterMessage.newBuilder()
                .setBasicConfig(basicConfig)
                .setZkConfig(zkConfig)
                .setStorageConfig(storageConfig)
                .setReplicationConfig(replicationConfig)
                .setNetConfig(netConfig)
                .setCharsetConfig(charsetConfig)
                .setDbConfig(dbConfig)
                .setBinlogConnectConfig(binLogConnectConfig)
                .setHeartBeatConfig(heartBeatConfig)
                .setDdlSupportConfig(ddlSupportConifg)
                .build();

        return canalParameterMessage;
    }
}
