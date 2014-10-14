package dubbo;
/**
 * Title:DemoServiceImpl.java
 * 
 * Description:DemoServiceImpl.java
 * 
 * Copyright: Copyright (c) 2014-9-22
 * 
 * Company: IZENE Software(Shanghai) Co., Ltd.
 * 
 * @author duanyu@b5m.com
 * 
 * @version 1.0
 */
public class DemoServiceImpl implements DemoService {
	public String sayHello(String name) {
		String result = "Hello, my name is:" + name;
		return result;
	}
}
