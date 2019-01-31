# simple-web
基于spring开发的webapp，整合常用的功能类库，开箱即用，让开发者专注于业务设计  
备注：该项目已停止维护，请访问v2.0版本，v2.0版本特性
1. 使用gradle构建项目
2. 使用了spring-boot
3. 更好的模块划分，和开发调试模式

# quick start
在开始之前，需要配置好JDK1.8 + maven环境

## 1. 配置
环境变量

|参数名称|说明|
|:-|:-|
|BYTREES_LOG4J_FILE|日志文件位置|

|文件名称|说明|
|:-|:-|
|app.properties|应用基础配置，例如mysql连接等|


## 2. build
执行命令mvn clean package，编译成功后，会在target目录下生成bytrees.war文件，上传到tomcat服务后即可运行。


