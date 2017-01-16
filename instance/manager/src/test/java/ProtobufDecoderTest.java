import com.alibaba.otter.canal.instance.manager.protocol.CanalModelPacket;
import com.alibaba.otter.canal.instance.manager.protocol.ProtobufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import netty.InboundHandler1;
import org.junit.Test;

/**
 * @author Wan Kaiming on 2016/12/30
 * @version 1.0
 */
public class ProtobufDecoderTest {

    /**
     *
     * 测试protobuf解码CanalModelPacket的能力。
     *
     */
    @Test
    public void testProtobufDecoder(){


        //创建嵌入式Channel用于发送encode之后的数据
        EmbeddedChannel ch1 = new EmbeddedChannel();




        //OutboundHandler子类放前面，放在InboundHandler子类之后的话，根据往回逆序调用的话，一个OutboundHandler都不会调用的
        ch1.pipeline().addLast(new LoggingHandler(LogLevel.INFO));  //log handler1:一开始作为InboundHandler，会使用channelRead方法；之后会作为OutboundHandler再执行一遍，打印encoder之后的消息(write方法)并且flush
        ch1.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
        ch1.pipeline().addLast(new ProtobufEncoder());

        ch1.pipeline().addLast(new LoggingHandler(LogLevel.INFO));  //log handler2:一开始作为InboundHandler，会使用channelRead方法；之后会作为OutboundHandler再执行一遍，打印encoder之前的消息并且flush
        ch1.pipeline().addLast(new InboundHandler1());              //InboundHandler1会产生一个protobuf定义的canal parameter实例,PS：调用InboundHandler之后开始走Outbound的事件流了

        ch1.writeInbound("Give a example message to avtive the channel...");

    }


    @Test
    public  void testLengthFieldBasedFrameDecoder() {
        ByteBuf in = Unpooled.buffer(16);
        in.writeByte(0xCA);
        in.writeShort(0x0010);
        in.writeByte(0xFE);
        in.writeBytes("HELLO, WORLD".getBytes());

        EmbeddedChannel channel = new EmbeddedChannel();
        channel.pipeline().addLast(new LoggingHandler(LogLevel.INFO));
        channel.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 1, 2, -3, 3));
        channel.pipeline().addLast(new LoggingHandler(LogLevel.INFO));
        channel.writeInbound(in);

    }


}
