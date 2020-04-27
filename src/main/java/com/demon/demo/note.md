### SpringBoot 官方文档 https://docs.spring.io/spring-boot/docs/

### SpringBoot http 协议开发

##### 配置

1. @SpringBootApplication = @SpringBootConfiguration + @EnableAutoConfiguration + @ComponentScan
    一个注解顶三个注解
2. @RestController = @Controller + @ResponseBody
    @RestController 和 @RequestMapping 是SpringMVC 的注解
    @ResponseBody 会自动将返回的内容转为字符串


##### Get 请求实战

* 单一参数
```
@RequestMapping(path="/{id}", method = RequestMethod.GET)
public String getUser(@PathVariable String id)
```
* 多个参数
```
对外参数建议设置为小写字母+下划线，以防止某些语言不支持驼峰命名法
@RequestMapping(path="/{dept_id}/{user_id}", method = RequestMethod.GET)
public String getUser(@PathVariable("dept_id") String deptId, @PathVariable("user_id") String userId)
```

* 简化注解

@GetMapping = @RequestMapping(method = RequestMethod.GET)
@PostMapping = @RequestMapping(method = RequestMethod.POST)
@PutMapping = @RequestMapping(method = RequestMethod.PUT)
@DeleteMapping = @RequestMapping(method = RequestMethod.DELETE)

* @RequestParam 可以设置默认值，指定参数是否必输，设置对外的参数名
* @RequestMapping + @RequestBody 映射实体类，http 请求头设置content-type 为 application/json，使用body 方式传输数据
* @RequestHeader 获取请求头数据
* HttpServletRequest request 自动注入获取参数

##### Jackson 自动处理字段
* @JsonIgnore 指定字段不返回
* @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale="zh", timezone = "GMT+8") 日期格式化
* @JsonInclude(JsonInclude.Include.NON_NULL) null 值不返回
* @JsonProperty("account") 指定别名

##### SpringBoot 目录文件结构
* 目录结构
```
src/main/java  存放代码
src/main/resources
    static  存放静态文件，比如 css、js、image，访问方法 localhost:8080/js/main.js
    templates  存放静态页面，比如 jsp、html
    config  存放配置文件，application.properties
    resources  其他资源文件，比如 脚本文件
```
* 静态资源文件的加载顺序

SpringBoot 默认会按照以下顺序查找相应的路径，META/resources > resources > static > public

* 非默认路径下的html 解析需要引入thymeleaf，由于 templates 不在默认的查找路径中，需要在controller 中跳转(FileController.index())

* 可以修改SpringBoot 默认的资源加载路径，spring.resources.static-locations=classpath:/META-INF/resources/, classpath:/resources/, classpath:/static/, classpath:/public/


##### 文件上传

1. 使用MultipartFile 对象来接收上传的文件，transferTo 方法用于文件保存
2. 文件大小的限制可以使用配置文件来设置，默认单文件 1MB
```
# 单文件大小
spring.servlet.multipart.max-file-size=3MB
# 总文件大小
spring.servlet.multipart.max-request-size=20MB
```

[web-properties 官方文档](https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-application-properties.html#web-properties)


### 热部署 Dev-tool

[Dev-tools 官方文档](https://docs.spring.io/spring-boot/docs/2.1.0.RELEASE/reference/htmlsingle/#using-boot-devtools)

```
引入依赖，该依赖会在开发时生效，打包后的环境会自动禁用
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <optional>true</optional>
</dependency>

IDEA 需要设置运行时自动编译程序，才可生效

指定文件不触发热部署 spring.devtools.restart.exclude=static/**,public/**

手工触发热部署 spring.devtools.restart.trigger-file=devtools-trigger.txt
修改了该文件，才会触发热部署；而修改了代码不会
```

### 配置文件

[SpringBoot 默认的配置文件](https://docs.spring.io/spring-boot/docs/2.1.0.RELEASE/reference/htmlsingle/#common-application-properties)

##### 配置文件自动映射

* 加载配置文件到Controller 的属性

> Controller 上配置资源文件 @PropertySource("classpath:application.properties")

> 在属性上配置映射的资源文件key
```
@Value("${upload.file.path}")
private String filePath;
```

* 加载配置文件到配置类中

> 配置类添加相应注解
```
@Component
@PropertySource("classpath:application.properties")
@ConfigurationProperties
public class ServerSettings {...}
```
> 配置类对应的属性上添加注解
```
@Value("${test.name}")
private String name;
@Value("${test.domain}")
private String domain;
```
> 使用配置类时，需IOC 对象注入才能使用
```
@Autowired
private ServerSettings serverSettings;
```
> 若属性名称和配置文件中的名称一致（完全一致或去除前缀后一致），则可通过名称直接注入属性值，不需要@Value 注解
```
# 去除前缀后一致的，通过@ConfigurationProperties 指定前缀
@ConfigurationProperties(prefix = "test.prop")
```


### 单元测试

> 引入依赖 spring-boot-starter-test

> 测试类使用以下注解
```
@RunWith(SpringRunner.class)    // 底层用JUnit  继承 SpringJUnit4ClassRunner
@SpringBootTest(classes = {Application.class})
```

> 单元测试方法使用@Test 注解

> @Before 单元测试之前执行  @After 单元测试之后执行

> 使用MockMvc
```
# 测试类增加配置注解
@AutoConfigureMockMvc

# 使用MockMvc 进行mvc 请求的测试
MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
System.out.println(mvcResult.getResponse().getContentAsString());

# perform 使用MockMvcRequestBuilders 构造一个mock 请求
# andExpect 校验MockMvcResultMatchers 的验证规则结果
# andReturn 返回MvcResult
```

### 全局异常处理

[全局异常处理类](https://github.com/windfish/spring-boot-learn/blob/master/src/main/java/com/demon/spring_boot/exception/BaseException.java)
> @ControllerAdvice 定义全局异常处理类
> @ExceptionHandler 定义函数针对的异常类型
> 默认的异常处理返回的ModelAndView，需要结合页面解析器反馈到页面上，比如，thymeleaf
> 也可以增加@ResponseBody 直接返回字符串到页面上


### SpringBoot war 包方式启动

> 在pom.xml 中将打包形式改为 <packaging>war</packaging>，并添加项目名称<finalName>springboot-learn</finalName>
> 修改启动类，使其继承SpringBootServletInitializer，并实现对应方法
```
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

    public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
```

### SpringBoot 过滤器Filter

> 默认加载的Filter
```
characterEncodingFilter
hiddenHttpMethodFilter
formContentFilter
requestContextFilter
```

> filter 的优先级

1. Ordered.HIGHEST_PRECEDENCE
2. Ordered.LOWEST_PRECEDENCE
3. 值越大，优先级越多；自定义的Filter，避免和默认的Filter 优先级一样，不然会冲突

> 自定义Filter

1. 使用Servlet 3.0 的注解进行配置
2. 启动类里增加 @ServletComponentScan(value = {"com.demon.demo.filter"})，扫描对应的包
3. 新建一个Filter 类，实现接口 javax.servlet.Filter，并实现对应的方法
4. Filter 中调用filterChain.doFilter 方法来控制是否运行请求通过
5. @WebFilter(urlPatterns = "/test_filter/*", filterName = "myTestFilter") 注解，指定该类为Filter，并定义需要拦截的url
6. filter 的抛出异常，@ControllerAdvice + @ExceptionHandler 的全局异常捕获不到


###### Servlet 3.0 自定义原生Servlet

1. 定义一个类，继承 HttpServlet
2. 类上增加注解 @WebServlet(name = "userServlet", urlPatterns = "/api/v1/test/servlet")
3. 实现 doGet 和 doPost 方法
4. 启动类增加 @ServletComponentScan(value = {"com.demon.demo.servlet"})，扫描对应的包

###### 使用Servlet 3.0 注解自定义原生Listener 监听器

> 常用的监听器
```
servletContextListener 应用上下文启动和关闭时就会调用，主要做资源初始化和销毁
httpSessionListener HttpSession 创建和销毁时会调用
servletRequestListener ServletRequest 创建和销毁时会调用，主要做统计
```

> 定义一个类，继承对应的listener，使用@WebListener 注解，启动类增加@ServletComponentScan

### SpringBoot 自定义拦截器

> 自定义拦截器类实现HandlerInterceptor 接口，并实现其对应的方法
```
preHandle 进入Controller 之前执行
postHandle 调用完Controller 之后，视图渲染之前执行；若Controller 出现了异常，则不会执行该方法
afterCompletion 整个请求完成之后执行，不管有没有异常，通常用于资源清理
```
> 使用@Configuration 注解定义拦截器的配置类，需继承WebMvcConfigurer，实现addInterceptors 方法注册相应的拦截器，多个拦截器按照注册的顺序依次拦截

> Filter 和 Interceptor 有什么区别

1. Filter 是基于函数回调doFilter()，Interceptor 是基于AOP 思想
2. Filter 只在Servlet 前后起作用，Interceptor 可以深入到方法前后、异常抛出前后等起作用
3. Filter 和 Interceptor 的执行顺序是：过滤前 --> 拦截前 --> controller --> 拦截后 --> 过滤后


### SpringBoot 整合freemarker

[freemarker 配置参数](https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-application-properties.html#templating-properties)


### 整合MyBatis

> 引入依赖，mybatis-spring-boot-starter、mysql-connector-java、druid

> 加入配置信息
```
spring.datasource.url=jdbc:mysql://test.db:3306/test?useUnicode\=true&characterEncoding\=utf8
spring.datasource.username=test
spring.datasource.password=test
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
# 使用的数据源，默认数据源（com.zaxxer.hikari.HikariDataSource）
#spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
# 打印sql 语句
mybatis.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl
```

> 启动类增加mapper 扫描，@MapperScan("com.demon.demo.mapper");

> 开发对应的mapper、service、controller









