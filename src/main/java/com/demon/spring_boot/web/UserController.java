package com.demon.spring_boot.web;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demon.spring_boot.entity.User;
import com.demon.spring_boot.entity.UserXml;

//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/users")
public class UserController {

    static ConcurrentHashMap<Long, User> users = new ConcurrentHashMap<>();
    
    @ApiOperation("获取用户列表")
    @RequestMapping(value="/", method=RequestMethod.GET)
    public List<User> getUserList(){
        /*
         * 处理 /users/ 的 GET 请求，用来获取玩家列表
         * 还可以通过 @RequestParam 从页面中传递参数，进行条件查询或翻页信息的传递
         */
        return new ArrayList<>(users.values());
    }
    
    @ApiOperation(value="获取用户详细信息", notes="根据用户id 获取用户信息")
//    @ApiImplicitParam(name="id", value="用户ID", required=true, dataType="Long")
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public User getUser(@PathVariable @ApiParam(value="用户ID", required=true) Long id){
        /*
         * 处理 /users/{id} 的GET 请求，用来获取id对应的user 信息
         */
        return users.get(id);
    }
    
    @ApiOperation("创建用户")
//    @ApiImplicitParam(name="user", value="用户详细实体user", required=true, dataType="User")
    @RequestMapping(value="/", method=RequestMethod.POST)
    public String postUser(@ModelAttribute @ApiParam(value="用户实体", required=true) User user){
        /*
         * 处理 /users/ 的 POST 请求，用来创建user
         * 除了 @ModelAttribute 绑定参数之外，还可以通过 @RquestParam 从页面中传递参数
         */
        users.put(user.getId(), user);
        return "success";
    }
    
    @ApiOperation(value="更新用户信息", notes="根据用户id 更新用户信息")
    /*@ApiImplicitParams({
        @ApiImplicitParam(name="id", value="用户ID", required=true, dataType="Long"),
        @ApiImplicitParam(name="user", value="用户信息user", required=true, dataType="User")
    })*/
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public String putUser(@PathVariable @ApiParam(value="用户ID", required=true) Long id, @ModelAttribute @ApiParam(value="用户实体", required=true) User user){
        /*
         * 处理 /users/{id} 的PUT 请求，用来更新user
         */
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return "success";
    }
    
    @ApiOperation(value="删除用户信息", notes="根据用户id 删除用户信息")
//    @ApiImplicitParam(name="id", value="用户ID", required=true, dataType="Long")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable @ApiParam(value="用户ID", required=true) Long id){
        /*
         * 处理 /users/{id} 的 DELETE 请求，用来删除user
         */
        users.remove(id);
        return "success";
    }
    
    @PostMapping(value="/userXml", consumes=MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public UserXml create(@RequestBody UserXml user){
        user.setName("learnSpringBoot: "+user.getName());
        user.setAge(user.getAge() + 10);
        return user;
    }
    
}
