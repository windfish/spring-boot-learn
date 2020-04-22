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


