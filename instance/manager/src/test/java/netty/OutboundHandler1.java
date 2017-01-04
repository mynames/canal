package netty;

import com.alibaba.otter.canal.instance.manager.protocol.CanalModelPacket;
import com.alibaba.otter.canal.instance.manager.protocol.ProtobufUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * 初始化default protobuf CanalModelMessage Instance勇于发送
 *
 * @author Wan Kaiming on 2017/1/4
 * @version 1.0
 */
public class OutboundHandler1 extends ChannelOutboundHandlerAdapter{

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {

        //准备一个入站消息,这里入站消息是一个Protobuf消息的实例
        CanalModelPacket.CanalModelMessage canalModelMessage = ProtobufUtils.getDefaultCanalModeMessage();

        System.out.println("Begin to write out data...");
        super.write(ctx, canalModelMessage, promise);

        //刷出消息
        ctx.flush();

        System.out.println("Finish to write out data...");
    }


}
