package com.wangyou.ktp_data.service;

import com.wangyou.ktp_data.entity.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {
    // 创建用户
    public Integer createUser(User user);
    // 更新信息
    public Integer updateInfo(User user);
    // 更新头像
    public Integer updateHeader(MultipartFile file, String uid, HttpServletRequest request);
    // 下载头像
    public void downloadHeader(String name, HttpServletResponse response);
    // 根据id查找
    public User findById(String uid);
    // 根据用户名查找
    public User findByUsername(String username);
}
