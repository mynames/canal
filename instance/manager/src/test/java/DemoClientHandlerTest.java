import com.alibaba.otter.canal.instance.manager.netty.CanalNettyClient;
import org.junit.Test;

/**
 * @author Wan Kaiming on 2016/12/23
 * @version 1.0
 */
public class DemoClientHandlerTest {

    @Test
    public void testDemoClientHandler() throws InterruptedException {
        CanalNettyClient client = new CanalNettyClient("127.0.0.1", 13333);
        client.start();
    }

}
