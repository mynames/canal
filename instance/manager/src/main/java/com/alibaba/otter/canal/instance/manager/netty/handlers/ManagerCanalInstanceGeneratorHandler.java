package com.alibaba.otter.canal.instance.manager.netty.handlers;

import com.alibaba.otter.canal.instance.core.CanalInstance;
import com.alibaba.otter.canal.instance.core.CanalInstanceGenerator;
import com.alibaba.otter.canal.instance.manager.CanalConfigClient;
import com.alibaba.otter.canal.instance.manager.CanalInstanceWithManager;
import com.alibaba.otter.canal.instance.manager.model.Canal;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 基于manager生成对应的{@linkplain CanalInstance}
 *
 * 把CanalInstance Generator改成一个handler，来利用manager消息来初始化Canal config
 *
 * @author jianghang 2012-7-12 下午05:37:09
 * @version 1.0.0
 */
public class ManagerCanalInstanceGeneratorHandler extends SimpleChannelInboundHandler implements CanalInstanceGenerator {

    private CanalConfigClient canalConfigClient;

    public CanalInstance generate(String destination) {
        Canal canal = canalConfigClient.findCanal(destination);
        String filter = canalConfigClient.findFilter(destination);
        return new CanalInstanceWithManager(canal, filter);
    }

    // ================ setter / getter ================

    public void setCanalConfigClient(CanalConfigClient canalConfigClient) {
        this.canalConfigClient = canalConfigClient;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        //这里假设获取的消息即为Canal Model
        canalConfigClient.setCanalModel((Canal) msg);

    }
}
