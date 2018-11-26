package com.demon.spring_boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <pre>
 * Application.java 同级创建 swagger2 的配置类
 * 通过 http://localhost:8080/swagger-ui.html 访问API 文档
 * 
 * @Configuration 注解让Spring 来加载该类配置
 * @EnableSwagger2 注解，用来启用 Swagger2
 * 
 * @Bean 方法级别的注解，表示使用该方法构造一个bean，主要用在@Configuration 注解的类中，也可以用在@Component 注解的类里。添加的 bean 的id 是方法名
 * 
 * </pre>
 * 
 * @author xuliang
 * @since 2018年11月26日 下午3:30:09
 *
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi(){
        /*
         * select() 会返回 ApiSelectorBuilder，用来控制哪些接口暴漏给Swagger 来展现
         * Swagger 会扫描定义的包路径下所有Controller 定义的API，生成接口文档，可以使用@ApiIgnore 注解剔除
         * 通过 @ApiOperation 注解来给API增加说明，通过@ApiParam、@ApiImplicitParams、@ApiImplicitParam 注解来给参数增加说明。
         * @ApiModel、@ApiModelProperty 在实体类中增加说明
         */
        return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.demon.spring_boot.web"))
                    .paths(PathSelectors.any())
                    .build();
    }
    
    private ApiInfo apiInfo(){
        /*
         * api 基本信息，会展示在文档页面中
         */
        return new ApiInfoBuilder()
                    .title("Spring Boot 中使用 Swagger2 构建 RESTful APIs")
                    .description("RESTful APIs")
                    .termsOfServiceUrl("https://github.com/windfish/spring-boot-learn")
                    .contact("fish")
                    .version("1.0")
                    .build();
    }
    
}
