package com.demon.spring_boot;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.demon.spring_boot.entity.CustomProperties;

/*
 * perform：执行一个RequestBuilder请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理；
 * andExpect：添加ResultMatcher验证规则，验证控制器执行完成后结果是否正确；
 * andDo：添加ResultHandler结果处理器，比如调试时打印结果到控制台；
 * andReturn：最后返回相应的MvcResult；然后进行自定义验证/进行下一步的异步处理；
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTests {

    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private MockMvc mvc;
	@Test
	public void getHello() throws Exception {
	    mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
	        .andExpect(MockMvcResultMatchers.status().isOk())
	        .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("Hello World.")));
	}
	
	@Autowired
	private CustomProperties customProperties;
	@Test
	public void getProperties(){
	    Assert.assertEquals(customProperties.getName(), "自定义名称");
	    Assert.assertEquals(customProperties.getTitle(), "学习Spring Boot");
	    logger.info(customProperties.getDesc());
	    logger.info("-------------------------------");
	    logger.info("随机字符串："+customProperties.getValue());
	    logger.info("随机int数："+customProperties.getNumber());
	    logger.info("随机long数："+customProperties.getBigNumber());
	    logger.info("10以内随机数："+customProperties.getTest1());
	    logger.info("10到20随机数："+customProperties.getTest2());
	}
	
	@Test
	public void testUserController() throws Exception{
	    RequestBuilder request = null;
	    MvcResult result = null;
	    
	    // 1、查询user 列表
	    request = MockMvcRequestBuilders.get("/users/");
	    mvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
	        .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("[]")));
	    
	    // 2、新增一个user
	    request = MockMvcRequestBuilders.post("/users/")
	                        .param("id", "1")
	                        .param("name", "测试用户")
	                        .param("age", "20");
	    mvc.perform(request).andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("success")));
	    
	    // 3、获取user 列表
	    request = MockMvcRequestBuilders.get("/users/");
	    mvc.perform(request).andDo(MockMvcResultHandlers.print());
	    
	    // 4、更新user 数据
	    request = MockMvcRequestBuilders.put("/users/1")
	                        .param("name", "终极测试用户")
	                        .param("age", "30");
	    mvc.perform(request).andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("success")));
	    
	    // 5、get 一个id 为1的user
	    request = MockMvcRequestBuilders.get("/users/1");
	    result = mvc.perform(request).andReturn();
	    logger.info(result.getResponse().getContentAsString());
	    
	    // 6、删除id 为1 的user
	    request = MockMvcRequestBuilders.delete("/users/1");
	    mvc.perform(request).andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("success")));
	    
	    // 7、获取user 列表
	    request = MockMvcRequestBuilders.get("/users/");
	    result = mvc.perform(request).andReturn();
	    logger.info(result.getResponse().getContentAsString());
	}
	
	@Test
	public void testLocalDate() throws Exception{
	    RequestBuilder request = MockMvcRequestBuilders.post("/localdto")
	                    .param("userName", "test")
	                    .param("birthday", "2011-05-06")
	                    .header("Content-Type", "application/json")
//	                    .content("{\"userName\":\"test\", \"birthday\":\"2011-05-06\"}")
	                    ;
	    MvcResult result = mvc.perform(request).andReturn();
	    logger.info(result.getResponse().getContentAsString());
	}

}
