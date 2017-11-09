package com.lhl.test.zk;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

import java.io.IOException;

/**
 * Created by liuhonglin on 2017/11/8.
 */
public class ZkClientTest {

    static String servers = "10.127.92.182:2181,10.127.92.182:2182,10.127.92.182:2183";
    static int session_timeout = 3000;
    static int connect_timeout = 3000;
    static ZkClient zkClient;

    public static void main(String[] args) {

        MyZkSerializer myZkSerializer = new MyZkSerializer();
        zkClient = new ZkClient(servers, session_timeout, connect_timeout, myZkSerializer);

        String createNewPath = "/myConfig/conf1";
        zkClient.createPersistent(createNewPath, true);
        if(zkClient.exists(createNewPath)) {
            System.out.printf("节点 %s 创建成功。\n", createNewPath);
        } else {
            System.out.printf("节点 %s 创建失败。\n", createNewPath);
        }

        System.out.printf("\"%s\"值: %s\n", createNewPath, zkClient.readData(createNewPath, true));

        zkClient.subscribeDataChanges(createNewPath, new MyZkDataListener());

        zkClient.writeData(createNewPath, "value2");

        boolean deleted = zkClient.deleteRecursive(createNewPath);



        zkClient.subscribeChildChanges("/brokers/topics", new MyZkChildListener());

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        zkClient.close();
    }

}
