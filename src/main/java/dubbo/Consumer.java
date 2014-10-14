package dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Title:Consumer.java
 * 
 * Description:Consumer.java
 * 
 * Copyright: Copyright (c) 2014-9-22
 * 
 * Company: IZENE Software(Shanghai) Co., Ltd.
 * 
 * @author duanyu@b5m.com
 * 
 * @version 1.0
 */
public class Consumer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "consumer.xml" });
		context.start();
		DemoService demoService = (DemoService) context.getBean("demoService159"); // 获取远程服务代理
		for(int i = 0;i < 500;i++){
			String hello = demoService.sayHello("duanyu" + i);
			System.out.println(hello);
		}
		System.out.println("----Consumer end----" + context);
		try {
			Thread.sleep(1000 * 60 * 60);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
