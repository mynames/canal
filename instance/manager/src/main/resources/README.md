#1. Canal Web Sinker Manager
##1.1 What is Canal Web Sinker Manager

Canal Web Sinker Manager: 支持将canal解析的结果实时写入kafka、hbase，并提供基于Web的canal实例、sinker任务配置、监控和告警。


#2. 设计

> 基本架构

Canal Server部分设计与原来相同，只不过原来的sink现在是有类型的。类型可能为kafka sinker或者hbase sinker。

现在讨论Canal Web Sinker Manager的架构设计，有以下要点：
1. Canal Web Sinker Manager的结构总体上分为web console和agent。
2. agent即为一个完整的canal server。我会扩展它的Manager实现，并且修改sink部分。agent负责和web console进行交互，接收web console的控制信息，并且定时返回统计信息
3. web console是一个web项目，向用户提供一个可视化管理界面。可以接收agent的统计信息可视化呈现。可以管理agent的sink job。

> web console和agent交互协议设计

交互协议可以参照canal来设计:
1. 权限验证：同canal
2. manager分配sinker任务：基于manager的管理，web console会发送信息给agent，指定sinker任务的详情
3. agent反馈agent定时发送各类统计信息反馈给manager
4. manager停止sink任务

PS： 参照canal设计，发送的具体统计消息都不使用protobuf编码。web console的控制信息使用protobuf编码。

话说为什么canal只把控制信息用protobuf编码？ 具体的binlog解析结果也有protobuf不是更好？难道netty3没protobufDecoder和Encoder?

> web console功能设计

优先完成内容：

1. 负责在canal instance初始化的时候向 web console请求配置信息，按照请求的配置信息初始化canal instance.
2. 定时收集canal instance解析binlog的统计信息
3. canal instance的健康状况信息
4. canal instance的生命周期控制

未来操作：
1. 引入到kafka、hbase的sinker
2. 定时收集各个Sinker的统计信息（包括健康信息、统计信息等等）
3. sinker任务的监控

> agent功能设计


#3 实现


##3.1 agent设计实现思路

1. ManagerCanalInstanceGenerator类改成netty的一个handler接收web console的配置信息来初始化配置。


###3.2 web console设计实现思路
web console就利用spring boot做成一个典型的WEB应用接收外部请求就可以。内部和agent的信息交互仍然用netty完成即可





#4. 项目现状
