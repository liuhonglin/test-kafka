package com.lhl.test.zk;

import org.I0Itec.zkclient.IZkDataListener;

/**
 * Created by liuhonglin on 2017/11/9.
 */
public class MyZkDataListener implements IZkDataListener {

    @Override
    public void handleDataChange(String path, Object newValue) throws Exception {
        System.out.printf("修改了数据：%s -> %s\n", path, newValue);
    }

    @Override
    public void handleDataDeleted(String path) throws Exception {
        System.out.printf("删除了节点: %s\n", path);
    }

}
