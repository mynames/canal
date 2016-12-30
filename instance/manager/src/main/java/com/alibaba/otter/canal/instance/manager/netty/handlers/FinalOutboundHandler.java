package com.alibaba.otter.canal.instance.manager.netty.handlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 该类是最后执行的OutboundHandler
 * @author Wan Kaiming on 2016/12/30
 * @version 1.0
 */
public class FinalOutboundHandler extends ChannelOutboundHandlerAdapter{

    private final static Logger logger = LoggerFactory.getLogger(FinalOutboundHandler.class);



    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {



        super.write(ctx, msg, promise);
    }
}
