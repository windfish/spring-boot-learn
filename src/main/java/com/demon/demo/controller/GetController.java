package com.demon.demo.controller;

import com.demon.demo.vo.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
  * http 协议的Get 请求
  *
  * author: xuliang
  * since: 2020/4/20 13:16
  **/
@RestController
public class GetController {

    private Map<String, Object> params = new HashMap<>();

    /**
      * 测试restful 协议，从路径中获取字段
      **/
    @RequestMapping(path = "/v1/{city_id}/{user_id}", method = RequestMethod.GET)
    public Object findUser(@PathVariable("city_id")String cityId, @PathVariable("user_id")String userId){
        params.clear();

        params.put("cityId", cityId);
        params.put("userId", userId);

        return params;
    }

    /**
     * 测试 GetMapping
     */
    @GetMapping(value = "/v1/page_user1")
    public Object pageUser(int from, int size){
        params.clear();
        params.put("from", from);
        params.put("size", size);
        return params;
    }

    /**
     * @RequestParam 默认值，是否必须的参数
     * defaultValue 默认值，name 对外的参数名，映射到from 变量
     */
    @GetMapping(value = "/v1/page_user2")
    public Object pageUser2(@RequestParam(defaultValue = "0", name = "page")int from, int size){
        params.clear();
        params.put("from", from);
        params.put("size", size);
        return params;
    }

    /**
     * bean 对象传参
     * 注意：1. 需指定http 请求头 content-type 为 application/json
     *      2. 使用body 传输数据
     * @RequestBody 用来接收请求体body 里的数据，一个请求只有一个RequestBody，前端需使用POST 方式提交数据
     */
    @RequestMapping("/v1/save_user")
    public Object saveUser(@RequestBody User user){
        params.clear();
        params.put("user", user);
        return params;
    }

    /**
     * 获取http 头信息
     */
    @GetMapping("/v1/get_header")
    public Object getHeader(@RequestHeader("access_token")String accessToken, String id){
        params.clear();
        params.put("id", id);
        params.put("accessToken", accessToken);
        return params;
    }

    /**
     * 获取HttpServletRequest
     */
    @GetMapping("/v1/test_request")
    public Object testRequest(HttpServletRequest request){
        params.clear();
        String id = request.getParameter("id");
        params.put("id", id);
        return params;
    }

}
