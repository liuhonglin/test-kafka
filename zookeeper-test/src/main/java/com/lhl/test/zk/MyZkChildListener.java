package com.lhl.test.zk;

import org.I0Itec.zkclient.IZkChildListener;
import sun.security.krb5.internal.CredentialsUtil;

import java.util.List;

/**
 * Created by liuhonglin on 2017/11/9.
 */
public class MyZkChildListener implements IZkChildListener {

    @Override
    public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {

        System.out.printf("子节点有变化：parentPath: %s, currentChilds: %s\n", parentPath, currentChilds);
    }
}
