package com.alibaba.otter.canal.instance.manager.netty;

import com.alibaba.otter.canal.instance.manager.netty.handlers.FinalOutboundHandler;
import com.alibaba.otter.canal.instance.manager.netty.handlers.ManagerCanalInstanceGeneratorHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * @author Wan Kaiming on 2016/12/23
 * @version 1.0
 */
public class CanalNettyClient {

    private static final Logger logger = LoggerFactory.getLogger(CanalNettyClient.class);

    private final String host;
    private final int port;

    private EventLoopGroup group;
    private ChannelFuture future;


    public CanalNettyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    //启动客户端
    public void start() throws InterruptedException {
        group = new NioEventLoopGroup();



            Bootstrap clientBootstrap = new Bootstrap();
            clientBootstrap.group(group).channel(NioSocketChannel.class).remoteAddress(new InetSocketAddress(host, port));

            clientBootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {


                    ch.pipeline().addLast(new ManagerCanalInstanceGeneratorHandler());
                    //PS：注意outbound先注册的，后执行。最后执行的是对消息做编码操作(用protobuf)，发送给server
                    ch.pipeline().addLast(new ProtobufEncoder());
                    ch.pipeline().addLast(new FinalOutboundHandler());

                }
            });

            future = clientBootstrap.connect().sync();
            logger.info("Connected");

    }


    //关闭客户端
    public void stop() throws InterruptedException {
        future.channel().closeFuture().sync();
        logger.info("Channels closed.");
        group.shutdownGracefully();
        logger.info("Netty client closed.");
    }





}
