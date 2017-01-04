import com.alibaba.otter.canal.instance.manager.protocol.CanalModelPacket;
import com.alibaba.otter.canal.instance.manager.protocol.ProtobufUtils;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import netty.InboundHandler1;
import netty.InboundHandler2;
import org.junit.Test;

/**
 * @author Wan Kaiming on 2016/12/30
 * @version 1.0
 */
public class HandlerTest {

    /**
     * 测试protbuf的encode效果
     *
     * ch1对数据利用protbuf encode来编码，然后发送
     * ch2拿到数据后进行解码打印
     *
     */
    @Test
    public void testProtobufDecoder(){


        //创建嵌入式Channel用于发送encode之后的数据
        EmbeddedChannel ch1 = new EmbeddedChannel();

        //InboundHandler1会产生一个protobuf实例

        ch1.pipeline().addLast(new LoggingHandler(LogLevel.INFO));
        ch1.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
        ch1.pipeline().addLast(new ProtobufEncoder());

        ch1.pipeline().addLast(new LoggingHandler(LogLevel.INFO));
        ch1.pipeline().addLast(new InboundHandler1());      //放在这个位置才有效






        ch1.writeInbound("Give a example message to avtive the channel...");




        //准备一个需要Decode 的protobuf类的实例用于解码
        CanalModelPacket.CanalModelMessage canalModelMessage = ProtobufUtils.getDefaultCanalModeMessage();


        //创建嵌入式Channel用于接收消息。 PS：必须在创建EmbeddedChannel时加入handler和decoder才有效
        EmbeddedChannel ch2 = new EmbeddedChannel();
        //先进行解码
        ch2.pipeline().addLast(new ProtobufVarint32FrameDecoder());
        ch2.pipeline().addLast(new ProtobufDecoder(canalModelMessage));
        ch2.pipeline().addLast(new InboundHandler2());



    }



}
