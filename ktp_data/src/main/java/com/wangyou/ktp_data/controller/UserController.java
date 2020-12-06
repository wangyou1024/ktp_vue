package com.wangyou.ktp_data.controller;

import com.wangyou.ktp_data.entity.RespBean;
import com.wangyou.ktp_data.entity.User;
import com.wangyou.ktp_data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/user")
@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public @ResponseBody String hello(){
        return "hello world";
    }

    @GetMapping("/login")
    public RespBean login(){
        return RespBean.error("尚未登录，请登录");
    }


    @PostMapping("/register")
    public @ResponseBody RespBean register(@RequestBody User user){
        Integer result = userService.createUser(user);
        if (result == 1){
            return RespBean.ok("注册成功");
        }else {
            return RespBean.build(403,"注册失败");
        }
    }

    @PostMapping("/updateInfo")
    public @ResponseBody Integer updateInfo(@RequestBody User user){
        return userService.updateInfo(user);
    }

    @PostMapping("/updateHeader")
    public String updateHeader(MultipartFile file, @RequestParam String uid, HttpServletRequest request){
        Integer result = this.userService.updateHeader(file, uid, request);
        return result == 1?"successful":"unsuccessful";
    }

    @RequestMapping("/downloadHeader")
    public void downloadHeader(@RequestParam String name, HttpServletResponse response){
        this.userService.downloadHeader(name,response);
    }

    @GetMapping("/findUserById")
    public @ResponseBody User findUserById(@RequestParam String uid){
        return userService.findById(uid);
    }

}
