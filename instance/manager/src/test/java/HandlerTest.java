import com.alibaba.otter.canal.instance.manager.model.Canal;
import com.alibaba.otter.canal.instance.manager.model.CanalParameter;
import com.alibaba.otter.canal.instance.manager.netty.handlers.FinalOutboundHandler;
import com.alibaba.otter.canal.instance.manager.netty.handlers.ManagerCanalInstanceGeneratorHandler;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import org.junit.Test;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wan Kaiming on 2016/12/30
 * @version 1.0
 */
public class HandlerTest {

    /**
     * 测试所有的Handler
     */
    @Test
    public void testMyHandlers(){


        Canal canal = initialDefaultCanalModel();


        //创建嵌入式Channel
        EmbeddedChannel ch = new EmbeddedChannel();

        //以下代码要先定义生成自己的protobuf类  CanalConfigMessage
        //ch.pipeline().addLast(new ProtobufDecoder(CanalConfigMessage.getDefaultInstance()));
        ch.pipeline().addLast(new ManagerCanalInstanceGeneratorHandler());

        //PS：注意outbound先注册的，后执行。最后执行的是对消息做编码操作(用protobuf)，发送给server
        ch.pipeline().addLast(new ProtobufEncoder());
        ch.pipeline().addLast(new FinalOutboundHandler());


        //写入Canal实例





    }


    /**
     *
     * @return 返回一个用于测试的合法Canal实例
     */
    private Canal initialDefaultCanalModel(){
        Canal defaulCanalModel = new Canal();

        //构造一个默认CanalParameter用于生成Canal实例
        CanalParameter canalParameter = new CanalParameter();
        canalParameter.setCanalId(1L);
        canalParameter.setPort(11111);
        List<String> zkList = new ArrayList<>();
        zkList.add("10.45.10.33:2181/kaiming-canal");
        canalParameter.setZkClusters(zkList);
        List<InetSocketAddress> dbAddressesList = new ArrayList<>();
        dbAddressesList.add(new InetSocketAddress("10.45.10.33",3306));
        canalParameter.setDbAddresses(dbAddressesList);
        canalParameter.setDbUsername("canal");
        canalParameter.setDbPassword("canal");
        canalParameter.setSlaveId(1234L);
        canalParameter.setIndexMode(CanalParameter.IndexMode.MEMORY);


        //初始化defaulCanalModel中的成员变量
        defaulCanalModel.setCanalParameter(canalParameter);
        defaulCanalModel.setId(1L);
        defaulCanalModel.setName("example");
        return defaulCanalModel;

    }
}
