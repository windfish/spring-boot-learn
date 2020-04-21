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


