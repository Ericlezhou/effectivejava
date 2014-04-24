effectivejava
=============

some samples worth to be noted in the guide book

1. note1-about the service-provider-framework based the static factory method.
   com.effective.note1 该包下的程序模拟了一个“服务提供者框架”，服务提供者框架包含四部分：服务接口，由提供者实现；提供者注册API，这是系统用来注册实现，让客户端访问它们的；服务访问API，是客户端用来获取服务的实例的；服务提供者接口，这些提供者负责创建其服务实现的实例。我们熟知的JDBC就是依据“服务提供者框架”实现的,原理是静态工厂方法返回的类，在编写包含静态方法的类时可以不必存在。
    该包下的程序和jdbc相关的模块和该框架的对关系如下：
    Provider.java和JDBC下的Driver.java都是服务提供者接口
    Service.java和JDBC下的Connection.java都是服务接口
    Services.java下的静态方法void Services.registerDefaultProvider(..)和DriverManager.java下的void registerDriver(Driver driver)都是采用了静态方法实现的注册API
    Services.java下的静态方法Service newInstance(..)和DriverManager.java下的Connection getConnection(String url)都是采用了静态方法实现的服务访问API

2. to be continued...
