package netty;

import com.alibaba.otter.canal.instance.manager.protocol.CanalModelPacket;
import com.alibaba.otter.canal.instance.manager.protocol.ProtobufUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author Wan Kaiming on 2017/1/4
 * @version 1.0
 */
public class InboundHandler1 extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("Reading msg in InboundHandler1");

        //读完之后初始化一个protobuf 实例对象交给OutboundHandler
        CanalModelPacket.CanalModelMessage canalModelMessage = ProtobufUtils.getDefaultCanalModeMessage();
        System.out.println("Sending  canalModelMessage to Encoder...");
        ctx.write(canalModelMessage);
        ctx.flush();


    }
}
