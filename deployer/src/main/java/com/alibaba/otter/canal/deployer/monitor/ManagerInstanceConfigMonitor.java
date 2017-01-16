package com.alibaba.otter.canal.deployer.monitor;

import com.alibaba.otter.canal.common.AbstractCanalLifeCycle;
import com.alibaba.otter.canal.common.CanalLifeCycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Kaiming Wan 2017-1-16 下午22:36:06
 * @version 1.0
 */
public class ManagerInstanceConfigMonitor extends AbstractCanalLifeCycle implements InstanceConfigMonitor, CanalLifeCycle {

    private static final Logger logger = LoggerFactory.getLogger(ManagerInstanceConfigMonitor.class);


    public void register(String destination, InstanceAction action) {

    }

    public void unregister(String destination) {

    }

}
