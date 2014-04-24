package com.effectivejava.note1;
/*
 * 该包下的程序模拟了一个 “服务提供者框架”，服务提供者框架包含四部分：服务接口，由提供者实现；
 * 提供者注册API，这是系统用来注册实现，让客户端访问它们的；服务访问API，是客户端用来获取服
 * 务的实例的；服务提供者接口，这些提供者负责创建其服务实现的实例。我们熟知的JDBC就是依据
 * “服务提供者框架”实现的,原理是静态工厂方法返回的类，在编写包含静态方法的类时可以不必存在。
 * 
 * 该包下的程序和jdbc相关的模块和该框架的对关系如下：
 * 	1. Provider.java和JDBC下的Driver.java都是服务提供者接口
 *	2. Service.java和JDBC下的Connection.java都是服务接口
 *  3. Services.java下的静态方法void Services.registerDefaultProvider(..)和
 *     DriverManager.java下的void registerDriver(Driver driver)都是采用了静态方法实现的注册API
 *  4. Services.java下的静态方法Service newInstance(..)和
 *     DriverManager.java下的Connection getConnection(String url)都是采用了静态方法实现的服务访问API
 *  
 */
//提供者注册API，服务访问API
import java.util.HashMap;
import java.util.Map;


public class Services
{
	private Services()
	{
	}
	
	private static final Map<String, Provider> map = new HashMap<String, Provider>();
	
	public static final String DEFAULT_PROVIDER_NAME = "IBM";
	//提供者注册，类似于DriverManager.registerDriver(Driver driver)
	public static void registerDefaultProvider(Provider p)
	{
		registerProvider(DEFAULT_PROVIDER_NAME, p);
	}
	
	public static void registerProvider(String name, Provider p)
	{
		map.put(name, p);
	}
	//服务访问，类似于DriverManager.getConnection()
	public static Service newInstance()
	{
		return newInstance(DEFAULT_PROVIDER_NAME);
	}
	
	public static Service newInstance(String name)
	{
		Provider p = map.get(name);
		if(null == p)
		{
			throw new IllegalArgumentException("No provider registered with name:" + name);
		}
		return p.newService();
	}
	
	public static void main(String[] args)
	{
		Provider p = new ComputeProvider();
		
		Services.registerDefaultProvider(p);   //类似于jdbc中的DriverManager.registerDriver(..)
		
		Service service = Services.newInstance();  //类似于jdbc中的DriverManager.getConnection(..)
	
		service.doService();
		
		
	}
	
}
