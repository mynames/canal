package com.alibaba.otter.canal.instance.manager;

import com.alibaba.otter.canal.instance.manager.model.Canal;
import org.slf4j.Logger;

/**
 * 对应canal的配置
 *
 * CanalConfigClient是个配置客户端，采用netty实现
 * 
 * @author Kaiming Wan 2016-12-23
 * @version 1.0.0
 */
public class CanalConfigClient {


    private final Logger logger = org.slf4j.LoggerFactory.getLogger(CanalConfigClient.class);


    private Canal canalModel;




    /**
     * 根据对应的destinantion查询Canal信息
     */
    public Canal findCanal(String destination) {
        // TODO 根据自己的业务实现
        CanalConfigClient canalConfigClient = new CanalConfigClient();

        return canalConfigClient.getCanalModel();

        //throw new UnsupportedOperationException();
    }

    /**
     * 过滤表的正则
     *
     * 根据对应的destinantion查询filter信息
     */
    public String findFilter(String destination) {
        // TODO 根据自己的业务实现

        return ".*";

        //throw new UnsupportedOperationException();
    }


    /**
     * 使用默认配置初始化canal model
     *
     *
     */
    @Deprecated
//    private Canal initialDefaultCanalModel(){
//        Canal defaulCanalModel = new Canal();
//
//        //构造一个默认CanalParameter用于生成Canal实例
//        CanalParameter canalParameter = new CanalParameter();
//        canalParameter.setCanalId(1L);
//        canalParameter.setPort(11111);
//        List<String> zkList = new ArrayList<>();
//        zkList.add("10.45.10.33:2181/kaiming-canal");
//        canalParameter.setZkClusters(zkList);
//        List<InetSocketAddress> dbAddressesList = new ArrayList<>();
//        dbAddressesList.add(new InetSocketAddress("10.45.10.33",3306));
//        canalParameter.setDbAddresses(dbAddressesList);
//        canalParameter.setDbUsername("canal");
//        canalParameter.setDbPassword("canal");
//        canalParameter.setSlaveId(1234L);
//        canalParameter.setIndexMode(CanalParameter.IndexMode.MEMORY);
//
//
//        //初始化defaulCanalModel中的成员变量
//        defaulCanalModel.setCanalParameter(canalParameter);
//        defaulCanalModel.setId(1L);
//        defaulCanalModel.setName("example");
//        return defaulCanalModel;
//
//    }


    public Canal getCanalModel() {
        return canalModel;
    }


    public void setCanalModel(Canal canalModel) {
        this.canalModel = canalModel;
    }
}
