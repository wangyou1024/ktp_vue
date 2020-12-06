package com.wangyou.ktp_data.dao;


import com.wangyou.ktp_data.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


@Mapper
@Component(value="UserDao")
public interface UserDao {
    // 创建用户
    public Integer createUser(User user);
    // 更新信息
    public Integer updateInfo(User user);
    // 更新头像
    public Integer updateHeader(String header,String uid);
    // 根据id查找
    public User findById(String uid);
    // 根据用户名查找
    public User findByUsername(String username);

}
