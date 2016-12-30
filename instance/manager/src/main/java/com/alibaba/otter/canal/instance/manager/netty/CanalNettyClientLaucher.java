package com.alibaba.otter.canal.instance.manager.netty;

/**
 * @author Wan Kaiming on 2016/12/23
 * @version 1.0
 */
public class CanalNettyClientLaucher {
    public static void main(String[] args) throws InterruptedException {
        CanalNettyClient client = new CanalNettyClient("127.0.0.1", 13333);
        client.start();
    }
}
