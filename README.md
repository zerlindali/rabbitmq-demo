# rabbitmq-demo
  rabbitmq的练习
## Rabbit MQ搭建
  https://gper.club/articles/7e7e7f7ff3g58gceg64
  
## 使用maven构建rabbitmq
### 工程结构 
生产者 rabbitmq-publisher
消费者 rabbitmq-consumer
### 环境
- Windows 10
- Erlang 23.1
- RabbitMQ 3.8.9
- jdk8

## 使用spring-boot构建rabbitmq
### 环境版本
- Spring Boot 2.3.4.RELEASE
- JDK 1.8
### 工程结构  
  生产者 rabbitmq-publisher-springboot
  
  消费者 rabbitmq-consumer-springboot
### 开发步骤
  消费者：
1. 定义交换机，队列，绑定交换机和队列
2. 使用消息转换器，将接收到的对象消息转为JsonString.
3. 消费者监听指定的队列
4. 启动消费者
  生产者：
1. 自定义消息模板，将对象消息转为Json格式发送到交换机
2. 为生产者注入自定义消息模板
3. 定义send方法使用template发送消息
4. 执行测试方法发送消息
### 其他说明
  Spring AMQP默认使用的消息转换器是SimpleMessageConverter。
  
  Jackson2JsonMessageConverter 用于将消息转换为JSON后发送。
  
  FourthConsumer里面实现了Java对象与JSON报文的转换。
