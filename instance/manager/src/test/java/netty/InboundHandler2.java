package netty;

import com.alibaba.otter.canal.instance.manager.protocol.CanalModelPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 读取入站数据并且答应，作为辅助测试用的handler
 *
 * @author Wan Kaiming on 2017/1/3
 * @version 1.0
 */
public class InboundHandler2 extends ChannelInboundHandlerAdapter{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("Invoke InboundHandler2...");



        CanalModelPacket.CanalModelMessage canalModelMessage = (CanalModelPacket.CanalModelMessage) msg;

        System.out.println(canalModelMessage.getCanalParameterMessage().getZkConfig().getZkClusters(0));

        super.channelRead(ctx, msg);
    }
}
