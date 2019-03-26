package com.demo._2zkclientapi;

import com.demo._2zkclientapi.model.User;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.BytesPushThroughSerializer;

/**
 * 订阅节点的子节点内容的变化
 *
 * @author jerome_s@qq.com
 */
public class _9SubscribeDataChanges {

	public static void main(String[] args) throws InterruptedException {
		ZkClient zc = new ZkClient("localhost:2181", 10000, 10000, new BytesPushThroughSerializer());
		System.out.println("conneted ok!");
		zc.subscribeDataChanges("/node1", new ZkDataListener());
		User u = new User();
		u.setId(2);
		u.setName("zhou");
		zc.writeData("/node1",u);
		Thread.sleep(Integer.MAX_VALUE);
	}

	private static class ZkDataListener implements IZkDataListener {
		public void handleDataChange(String dataPath, Object data) throws Exception {
			System.out.println(dataPath + ":" + data.toString());
		}

		public void handleDataDeleted(String dataPath) throws Exception {
			System.out.println(dataPath);
		}
	}

	// 在shell操作，可以看到对应的输出
	
}
