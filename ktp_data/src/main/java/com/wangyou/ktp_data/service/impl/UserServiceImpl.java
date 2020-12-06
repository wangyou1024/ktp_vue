package com.wangyou.ktp_data.service.impl;

import com.wangyou.ktp_data.dao.AnswerDao;
import com.wangyou.ktp_data.dao.CourseDao;
import com.wangyou.ktp_data.dao.UserDao;
import com.wangyou.ktp_data.entity.User;
import com.wangyou.ktp_data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private String headerPath = "E:\\javaEE\\finaly\\file_upload\\header\\";

    @Autowired
    private UserDao userDao;

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private AnswerDao answerDao;

    @Override
    public Integer createUser(User user) {
        user.setUid(System.currentTimeMillis() + "");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = user.getPassword();
        String newPassword = encoder.encode(password);
        user.setPassword(newPassword);
        Integer result = userDao.createUser(user);
        return result;
    }

    @Override
    public Integer updateInfo(User user) {
        Integer result = userDao.updateInfo(user);
        // 给课程绑定教师信息
        courseDao.bindTeacherName(user.getName(),user.getUid());
        // 给答案绑定学生信息
        answerDao.updateUserInfo(user.getNumber(),user.getName(),user.getUid());
        return result;
    }

    @Override
    public Integer updateHeader(MultipartFile file, String uid, HttpServletRequest request) {
        String newName = "";
        try {
            String oldName = file.getOriginalFilename();
            newName = System.currentTimeMillis() + oldName.substring(oldName.lastIndexOf("."));
            courseDao.bindTeacherHeader(newName,uid);
            File folder = new File(headerPath, newName);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            file.transferTo(folder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Integer result = userDao.updateHeader(newName, uid);
        return result;
    }

    @Override
    public void downloadHeader(String name, HttpServletResponse response) {
        FileInputStream inputStream = null;
        OutputStream outputStream = null;
        // 默认图片
        if (name.equals("null")){
            name="1605956012583.jpg";
        }
        try {
            inputStream = new FileInputStream(headerPath + name);
            Integer i = inputStream.available();
            byte[] buff = new byte[i];
            inputStream.read(buff);
            response.setContentType("image/*");
            outputStream = response.getOutputStream();
            outputStream.write(buff);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public User findById(String uid) {
        return userDao.findById(uid);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByUsername(username);
    }
}
