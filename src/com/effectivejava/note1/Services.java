package com.effectivejava.note1;
/*
 * �ð��µĳ���ģ����һ�� �������ṩ�߿�ܡ��������ṩ�߿�ܰ����Ĳ��֣�����ӿڣ����ṩ��ʵ�֣�
 * �ṩ��ע��API������ϵͳ����ע��ʵ�֣��ÿͻ��˷������ǵģ��������API���ǿͻ���������ȡ��
 * ���ʵ���ģ������ṩ�߽ӿڣ���Щ�ṩ�߸��𴴽������ʵ�ֵ�ʵ����������֪��JDBC��������
 * �������ṩ�߿�ܡ�ʵ�ֵ�,ԭ���Ǿ�̬�����������ص��࣬�ڱ�д������̬��������ʱ���Բ��ش��ڡ�
 * 
 * �ð��µĳ����jdbc��ص�ģ��͸ÿ�ܵĶԹ�ϵ���£�
 * 	1. Provider.java��JDBC�µ�Driver.java���Ƿ����ṩ�߽ӿ�
 *	2. Service.java��JDBC�µ�Connection.java���Ƿ���ӿ�
 *  3. Services.java�µľ�̬����void Services.registerDefaultProvider(..)��
 *     DriverManager.java�µ�void registerDriver(Driver driver)���ǲ����˾�̬����ʵ�ֵ�ע��API
 *  4. Services.java�µľ�̬����Service newInstance(..)��
 *     DriverManager.java�µ�Connection getConnection(String url)���ǲ����˾�̬����ʵ�ֵķ������API
 *  
 */
//�ṩ��ע��API���������API
import java.util.HashMap;
import java.util.Map;


public class Services
{
	private Services()
	{
	}
	
	private static final Map<String, Provider> map = new HashMap<String, Provider>();
	
	public static final String DEFAULT_PROVIDER_NAME = "IBM";
	//�ṩ��ע�ᣬ������DriverManager.registerDriver(Driver driver)
	public static void registerDefaultProvider(Provider p)
	{
		registerProvider(DEFAULT_PROVIDER_NAME, p);
	}
	
	public static void registerProvider(String name, Provider p)
	{
		map.put(name, p);
	}
	//������ʣ�������DriverManager.getConnection()
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
		
		Services.registerDefaultProvider(p);   //������jdbc�е�DriverManager.registerDriver(..)
		
		Service service = Services.newInstance();  //������jdbc�е�DriverManager.getConnection(..)
	
		service.doService();
		
		
	}
	
}
