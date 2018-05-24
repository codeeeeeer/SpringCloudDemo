# Spring Cloud 学习笔记
## 微服务架构
微服务架构风格是一种使用一套小服务来开发单个应用的方式途径，每个服务运行在自己的进程中，服务之间相互协调，互相配合，为用户提供最终价值。服务之间使用轻量级机制通信，通常是HTTP的RESTful API，这些服务基于业务能力构建，并能够通过自动化部署机制来独立部署，这些服务使用不同的编程语言实现，以及不同数据存储技术，并保持最低限度的集中式管理。
## 微服务架构的优缺点
### 优点
1.每个服务足够内聚，足够小，代码容易理解，这样能聚焦一个指定的业务功能或业务需求。  
2.开发简单、开发效率高，一个服务可能就是专一的只干一件事。  
3.微服务是松耦合的，是有功能意义的服务，无论在开发阶段或部署阶段都是独立的。  
4.微服务能使用不同的语言进行开发。  
5.易于和第三方集成，微服务允许容易且灵活的方式集成自动部署，通过持续集成工具，如Jenkins,Hudson,bamboo。  
6.微服务允许你利用融合最新技术  
7.微服务只是业务逻辑代码，不会和HTML，CSS或其他界面组件混合  
8.每个微服务都有自己的存储能力，可以有自己的数据库，也可以有统一的数据库。
### 缺点
1.开发人员要处理分布式系统的复杂性。  
2.多服务运维的难度，随着服务的增加，运维的压力也在增大。  
3.系统部署依赖。  
4.服务间的通信成本。  
5.数据的一致性。  
6.系统集成测试。  
7.性能监控。
## 微服务框架Spring Cloud和Dubbo对比
### 对比表
| |Dubbo| Spring Cloud|
|:-: | :-: | :-: |
|服务注册中心|Zookeeper|Spring Cloud Netflix Eureka|
|服务调用方式|RPC|RESTful API |
|服务监控|Dubbo-monitor|Spring Boot Admin|
|断路器|不完善|Spring Cloud Netflix Hystrix|
|服务网关|无|Spring Cloud Netflix Zuul|
|分布式配置|无|Spring Cloud Config|
|服务跟踪|无|Spring Cloud Sleuth|
|消息总线|无|Spring Cloud Bus|
|数据流|无|Spring Cloud Stream|
|批量任务|无|Spring Cloud Task|
### 最大的区别
##### Spring Cloud抛弃了Dubbo的RPC通信，采用的是基于HTTP的REST方式。
严格来说，这两种方式各有优劣。虽然从一定程度上来说，后者牺牲了服务的调用性能，但也避免了上面提到的原生RPC带来的问题。而且REST相比RPC更为灵活，服务提供方和调用方的依赖只依靠一纸契约，不存在代码级别的强依赖，这也在强调快速演化的微服务环境下，显得更为合适。
## Spring Cloud项目构件
### 构建父工程
1.新建父工程microservicecloud，切记Packaging模式是POM  
2.主要是定义pom文件，将后续各个子模块共用的jar包等统一提取出来，类似一个抽象父类，并在该pom文件中对所有子工程用到的jar包的版本进行管理
### 构建公共模块
1.在microservicecloud项目下新建module，命名为microservicecloud-api（在创建完成后，父工程中的pom会包含有该module，以后每次在此项目下新建module都会被pom包含）  
2.修改pom文件，引入本项目所需要用到的jar包  
3.新建公共类，如各种实体类Entities(如Department,可配合lombok使用简化代码)或工具类  
4.操作完成之后，需要mvn clean， mvn install一下，达到通用的目的，也就是说如果以后其他module需要用到该module中的内容的话，不用每个工程都定义一份，只需要在pom文件中引入本module就行了
### 构建provider模块，负责连接数据库并向外提供数据
1.新建数据库和table，并准备好必要的数据  
2.在microservicecloud项目下新建module，命名为microservicecloud-provider-dept-8001（module名字最好包含准备好的服务端口号和作用，因为到了后面会有很多微服务，加上这两点便于区分）  
3.修改pom文件，引入需要用到的公共模块如microservicecloud-api以及需要的jar包  
4.在resource目录下新建application.yml，在yml文件中，至少需要定义  
+ server.port(便于将来在浏览器中访问)
+ mybatis相关配置 
+ spring.application.name（微服务名称，以后其他微服务可以通过这个名称来访问这个module接口）  
+  spring.datasource（因为要连接数据库，必要的如驱动类、url、username、password以及数据库连接池相关配置） 
  
  
5.完成mybatis.cfg.xml文件的编辑  
6.完成dao接口以及相应mapper.xml文件的编辑，使得实体类和数据库数据可以转化  
7.添加service接口和serviceImpl实现类，调用dao  
8.编写controller控制类，调用service相关，负责外界和本module的通讯  
9.添加SpringBootApplication启动类，负责启动这个微服务  
10.启动该微服务并进行测试。
### 构建consumer模块
1.在microservicecloud项目下新建module，命名为microservicecloud-consumer-dept-80  
2.修改pom文件，引入需要的公共模块和必要的jar包  
3.新建application.yml文件，指定server.port端口  
4.新建ConfigBean，负责Bean的管理（类似于以前的applicationContext.xml）  
5.新建ConsumerController,负责和外界通讯，并调用RestTemplate  
6.创建SpringBootApplication主启动类  
7.启动微服务并进行测试
## Eureka
### Eureka是什么
> Eureka是Netflix的一个子模块，也是核心模块之一。Eureka是一个基于REST的服务，也是定位服务，以实现云端中间层服务发现和故障转移。服务注册于发现对于微服务架构来说是非常重要的，有了服务发现与注册，只需要使用服务的标识符，就可以访问到服务，而不需要修改服务调用的配置文件了。功能类似于duboo的注册中心，比如Zookeeper。  

Eureka采用了C-S的设计架构，Eureka作为服务注册功能的服务器，它是服务注册中心。而系统的其他微服务，使用Eureka的客户端连接到Eureka Server并维持心跳连接。这样系统的维护人员就可以通过Eureka Server来监控系统中各个微服务是否正常运行。Spring Cloud的一些其他模块（比如Zuul）就可以通过Eureka Server来发现系统中的其他微服务，并执行相关逻辑。  
#### Eureka Server
Server提供注册服务，各个节点启动后，会在Eureka Server中进行注册，这样的EurekaServer中的服务注册表会将存储所有可用服务节点的信息，服务节点的信息可以在外界中直接观看到。
#### Eureka Client
EurekaClient是一个Java客户端，用于简化EurekaServer的交互，客户端同时也具备一个内置的、使用轮训负载算法的负载均衡器。在应用启动后，将会向EurekaServer发送心跳（默认周期为30秒）。如果EurekaServer在多个心跳周期内没有接收到某个节点的心跳，EurekaServer将会从服务注册表中吧这个服务节点移除（默认是90秒）。
#### 三大角色
+ Eureka Server提供服务注册与发现  
+ Service Provider服务提供方将自身服务注册到Eureka，从而使服务消费方能够找到  
+ Service Consumer 服务消费方从Eureka获取注册服务列表，从而能够消费服务。  
  
### 构建Eureka微服务
#### 1.构建Eurekamodule
1.在microservicecloud下新建module，并命名为microservicecloud-eureka-7001  
2.修改pom文件，注意引入eureka-server的jar包以及其他必要的jar包  
3.新建applicaiton.yml,添加服务端口号、eureka服务端实例名称以及一些其他的关于eureka服务端的配置  
4.添加SpringBootApplication主启动类，为启动类添加@EnableEurekaServer注解标签  
5.启动微服务，进行测试，输入在yml中配置的hostname+port，访问Enreka主页
#### 2.将已有的微服务注册进eureka服务中心
修改microservicecloud-provider-dept-8001相关内容：  
1.修改pom文件，添加eureka客户端jar包和configjar包  
2.修改yml文件，添加eureka相应配置，将该微服务注册进指定地址 
3.修改主启动类，为启动类添加@EnableEurekaClient注解标签  
4.测试。启动微服务，在访问相应的eureka主页，可以发现该微服务的application-name(在yml中配置的)显示在eureka instances中
#### 3.完善微服务info信息
当点击eureka服务的instance链接时，报error page错误
1.修改microservicecloud-provider-dept-8001的pom文件，添加actuator依赖  
2.修改父工程的pom，构建build信息  
3.修改microservicecloud-provider-dept-8001的yml配置文件，添加info相应信息
### Eureka的自我保护机制
默认情况下，如果EurekaServer在一定时间内没有即受到某个微服务服务实例的心跳，EurekaServer将会注销该实例（默认90秒）。但是当网络分区故障时，微服务与EnrekaServer之间无法正常通信，以上行为可能变得非常危险——因为微服务本身其实是健康的，此时本不应该注销这个微服务。Eureka通过“自我保护模式”来解决这个问题——当EurekaServer节点在短时间内丢失过多客户端时，（可能发生了网络分区故障），那么这个节点就会进入自我保护模式。一旦进入该模式Eureka就会保护服务注册表中的信息，不在删除服务注册表中的数据（也就是不会注销任何微服务）。当网络故障恢复后，该Eureka Server节点会自动退出自我保护模式。  
在自我保护模式中，Eureka Server会保护服务注册表中的信息，不在注销任何服务实例。当他收到的心跳数重新恢复到阈值以上时，该Eureka Server节点就会自动退出自我保护模式。它的设计哲学就是宁可保留错误的服务注册信息，也不盲目注销任何可能健康的服务实例。 
综上，自我保护模式是一种应对网络异常的安全保护措施。
### Eureka的集群配置
1.在父工程下新建module，命名为microservicecloud-eureka-7002和microservicecloud-eureka-7003,将microservicecloud-eureka-7001的相关文件全部拷贝到这两个module中，然后再根据各自的具体情况作出修改  
2.在host文件中为127.0.0.1新增三个映射：eureka7001.com，eureka7002.com,eureka7003.com  
3.根据各自情况修改yml文件中的端口，hostname,defaultZone  
4.修改需要注册进eureka集群的相关module的yml配置文件，如microserivcecloud-provider-dept-8001，需要将defaultZone修改为http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka,http://eureka7003.com:7003/eureka  
5.将三个eureka server各自启动，用浏览器访问各自主页，可以在DS Replicas看到另外两个服务器
### Eureka 与 Zookeeper
#### Eureka遵循的是AP原则，Zookeeper遵循的是AP原则
+ A：Availability 可用性
+ C：Consistency 强一致性
+ P：Partition tolerance 分区容错性  
  
CAP理论的核心是：一个分布式系统不可能同时很好的满足一致性，可用性和分区容错性这三个需求，因此，根据CAP原理将NOSQL数据库分成了满足CA原则，满足CP原则和满足AP原则三大类：
+ CA：单点集群，满足一致性和可用性的系统，通常在可拓展性上不太强大。
+ CP：满足一致性和分区容错性的系统，通常性能不是特别高
+ AP：满足可用性和分区容错性的系统，通常对一致性要求低一些。  

##### Zookeeper保证CP
当向注册中心查询服务列表时，我们可以容忍注册中心返回的是几分钟前注册信息，但不能接受服务直接down掉不可用。也就是说，服务注册功能对可用性的要求要高于一致性，但是Zookeeper会出现这样一种情况，当master节点因为网络故障与其他节点失去联系时，剩余节点会重新进行leader选举。问题在于，选举leader的时间太长，30~120s，且选举期间整个Zookeeper集群都是不可用的，这就导致在选举期间注册服务瘫痪。在云部署的环境下，因为网络问题使得Zookeeper集群失去master节点是较大概率会发生的事，虽然服务能够最终恢复，但是在漫长的选举时间导致的注册长期不可用是不能容忍的。
##### Eureka保证AP
Eureka的各个节点是平等的，几个节点挂掉不会影响正常节点的工作，剩余节点依然可以提供注册和查询服务。而Eureka的客户端在向整个Eureka注册时如果发现连接失败，则会自动切换到其他节点，只要有一台Eureka还在，就能保证注册服务可用，只不过查询到的信息可能不是最新的。除此之外，Eureka还有一种自我保护机制，如果在15分钟内超过85%的节点都没有正常的心跳，那么Eureka就认为客户端与注册中心出现了网络故障，此时就会出现以下几种情况：
+ Eureka不在从注册列表中移除因为没有长时间没有收到心跳而应该过期的服务
+ Eureka仍然能够接受新服务的注册和查询请求，但是不会被同步到其他节点上（即保证当前节点依然可用）。
+ 当网络稳定时，当前实例新的注册信息会被同步到其他节点中。
  
###### Eureka可以更好地应对因为网络故障而导致部分节点失去联系的情况，而不会像Zookeeper那样使整个注册服务瘫痪
## Ribbon
#### 负载均衡
简单来说就是讲用户的请求平摊的分配到多个服务器上，从而达到系统的HA，常见的负载均衡软件有Nginx，LVS，硬件F5等
#### Ribbon
Spring Cloud Ribbon是基于Netflix Ribbon实现的一套客户端 负载均衡算法。  
简单来说，Ribbon是Netflix发布的开源项目，主要功能是提供客户端的软件负载均衡算法，将Netflix的中间服务连接在一起。Ribbon客户端组件提供一系列完善的配置项如连接超时，重试等等。简单来说，就是在配置文件中列出Load Balancer后面所有的机器，Ribbon会自动的帮助你基于某种规则（简单轮询，随机连接等）去连接这些机器。我们也可以使用Ribbon实现自定义的负载均衡算法
### Ribbon的初步配置
Ribbon是客户端的负载均衡 算法，所以在我们当前项目中，只要修改microservicecloud-consumer-dept-80  
1.修改pom文件，导入Ribbon相关依赖。  
2.修改application.yml文件，追加eureka的服务注册地址，表示以后访问provider会通过eureka，但是注意不要讲consumer注册进eureka。  
3.修改configBean，在RESTTemplate的Bean声明上加上注解@LoadBalanced。  
4.在主启动类上加上注解@EnableEurekaClient  
5.修改ConsumerController，将RestTemplate的访问地址形式改为根据服务名称访问，而不是地址+ 端口的形式，因为要实现负载均衡，就不能将服务路径写死  
6.参考microservicecloud-provider-dept-8001新建microservicecloud-provider-dept-8002和microservicecloud-provider-dept-8003，将microservicecloud-provider-dept-8001的内容拷贝到这两个module中，并根据各自的具体情况修改yml文件中端口号、数据库、instance-id等信息，但是不能修改application.name，因为consumer就是根据这个name来访问这三个微服务  
7.启动三个eureka server，启动三个provider，启动consumer，进行测试。
## Feign
Feign是一个声明式的Web服务客户端，使得编写Web客户端变得非常容易  
只需要创建一个接口，然后在上面添加注解即可
### Feign与Ribbon+RestTemplate
前面在使用Ribbon+RestTemplate时，利用RestTemplate对http请求的封装处理，行程里一套模块化的调用方法。但是在实际开发中，由于服务依赖的调用不止一处，往往一个接口会被多处调用，所以通常都会针对每个微服务自行封装一些客户端类来包装这些依赖服务的调用。所以，Feign再次基础上做了进一步的封装，由它来帮助我们定义和实现依赖服务接口的定义。在Feign的实现下，我们只需要创建一个接口并使用注解的方式来配置它（以前是dao接口上面标注Mapper注解，现在是一个微服务接口上面标注一个Feign注解即可），即可完成对服务提供方的接口绑定，简化了使用Spring  cloud Ribbon时，自动封装服务调用客户端的开发量。
### Feign的使用
1.参考microservicecloud-consumer-dept-80新建microservice-consumer-dept-feign,修改主启动类的名字  
2.修改pom文件，添加对feign的支持  
3.修改microservicecloud-api工程
+ 修改pom文件，添加对feign的依赖
+ 新建DeptClientService接口，并新增注解@FeignClient，并通过applicationName指定要访问的微服务
+ mvn clean + mvn install
  
4.新建ConsumerController，注入DeptClientService,然后就可以通过这个service访问微服务了
5.启动eureka集群，启动provider，启动feignClient，测试。
## Hystrix断路器
### 分布式系统面临的问题
复杂分布式体系结构中的应用程序有数十个依赖关系，每个依赖在某些时候将不可避免地失败。
### 雪崩效应
多个微服务之间调用的时候，假设微服务A掉用微服务B，微服务B又调用微服务C。。。。。这样形成一个链路，这就是所谓的扇出。如果扇出的链路上某个微服务的调用响应时间过长或者不可用，对微服务A的嗲用就是占用越来越多的系统资源，从而形成系统崩溃，所谓的雪崩效应。  
对于高流量的应用来说，单一的后端依赖可能会导致所有服务器上的所有资源都在几秒内饱和。比失败更糟糕的是，这些应用程序还可能导致服务之间的延迟增加，备份队列，线程和其他系统资源紧张，导致整个系统发生更多的级联故障。这些都表示需要对故障和延迟进行隔离和管理，以便单个依赖关系的失败，不能取消整个应用程序或系统。
### Hystrix
Hystrix是一个用于处理分布式系统的延迟和容错的开源库，在分布式系统里，许多依赖不可避免地会调用失败，比如超时、异常等，Hytrix能够保证在一个依赖出问题的情况下，不会导致整体服务失败，避免级联 故障，以提高分布式系统的弹性。  
断路器本身是一种开关设置，当某个服务单元发生故障之后，通过断路器的故障监控（类似熔断保险丝），向调用方返回一个符合预期、可处理的备选响应（FallBack），而不是长时间的等待或者抛出调用方无法处理的异常，这样就保证了服务调用方的线程不会被长时间、不必要的占用，从而避免了故障在分布式系统中的蔓延，乃至雪崩。  
熔断机制是应对雪崩效应的一种微服务链路保护机制。  
当扇出链路的某个微服务不可用或者相应时间太长时，会进行服务降级，进而熔断该节点微服务的调用，快速返回“错误”的相应信息。当检测到该节点微服务调用相应正常后恢复调用链路。  
在Spring Cloud中通过Hytrix实现熔断。Hytrix会监控微服务间的调用情况，当失败的调用到一定阈值，缺省是50秒内20次调用失败就会自动启动熔断机制。
### Hystrix实验
1.参考microservicecloud-provider-dept-8001，创建microservicecloud-provider-dept-hystrix  
2.修改pom文件，添加对Hytrix的依赖  
3.修改yml文件  
4.编写DeptController
+ 写一个可能出错的方法，例如getEntityById
+ 在数据出现某种情况是在方法内抛出一个异常
+ 为该异常情况编写一个相对应地处理措施方法，如processHystrix_get
+ 为getEntityById新增注解@HystrixCommand，并指定属性fallbackMethod，值为processHystrix_get

5.修改主启动类，并新增注解@EnableCircuitBreaker  
6.测试
### 服务降级
当整个系统资源快不够时，可以先将某些服务关掉，等资源充足时在开启回来。  
服务降级是在客户端完成的，与服务端没关系
### 服务降级测试
1.修改microservicecloud-api，根据已有的DeptService接口新建一个实现了FallBackFactory接口的类DeptClientServiceFallBackFatory，实现其create方法，返回一个用于fallBack调用的bean  
2.在DeptService的@FeignClient注解中添加fallBackFactory，指定class  
3.microservicecloud-api  mvn clean + mvn install  
4.修改microservice-consumer-dept-feign的yml文件，添加对熔断器的支持。  
5.测试，启动eureka集群，启动microservice-provider-dept-8001，启动microservice-consumer-dept-feign，正常访问测试。  
6.关闭microservice-provider-dept-8001，再访问，可以看到返回的是fallBack配置的内容
##### 此时provider已经down了，但是我们做了服务降级处理，让客户端在服务端不可用时也会获得提示信息，而不是挂起耗死服务器
## 总结
项目源码已经上传到了https://github.com/codeeeeeer/SpringCloudDemo