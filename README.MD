└─src
├─main
│  ├─assembly 【打包配置】
│  ├─config 【多环境配置文件目录】
│  │  ├─dev 【开发环境】
│  │  ├─product 【生产环境】
│  │  └─test 【测试环境】
│  ├─java 【java源代码】
│  │  └─com.iflyzunhong.sl
│  └─resources 【资源文件】
│  └─startup 【启动脚本】
└─test 【单元测试目录】
    ├─java 【单元测试代码】
       └─com.iflyzunhong.vvm3
    └─resources【单元测试资源文件，运行期间在classpath下】

###程序打包
利用maven的profile机制，实现多环境打包。例如：生产环境打包命令为：
```
mvn clean package -Pproduct
```

###开发说明
根据start.Main包的main方法开关，选择需要连接的外部集群；
```
新建业务逻辑时，通过新建业务类继承ServiceBase，并在consumerServiceRegister方法注册自己的业务逻辑类，实现业务调用；
```
log4j.properties中performance的logger对象是用来输出程序运行的性能数据，目前包括：
```
1、RMQ消息处理耗时； 2、存储过程调用耗时； 3、redis操作耗时；
```