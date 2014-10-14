package dubbo;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Title:Provider.java
 * 
 * Description:Provider.java
 * 
 * Copyright: Copyright (c) 2014-9-22
 * 
 * Company: IZENE Software(Shanghai) Co., Ltd.
 * 
 * @author duanyu@b5m.com
 * 
 * @version 1.0
 */
public class Provider {
	private static CountDownLatch latch = new CountDownLatch(1);
	
	private static final Provider instance = new Provider();
	
	private Provider(){
	}
	
	public static Provider getProvider(){
		return instance;
	}
	
	public void start(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "provider.xml" });
		context.start();
		System.out.println("----Provider Start----" + context);
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void stop(){
		latch.countDown();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		getProvider().start();
	}

}
