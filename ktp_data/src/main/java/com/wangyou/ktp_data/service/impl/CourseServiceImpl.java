package com.wangyou.ktp_data.service.impl;

import com.wangyou.ktp_data.dao.CourseDao;
import com.wangyou.ktp_data.dao.HomeworkDao;
import com.wangyou.ktp_data.dao.MessageDao;
import com.wangyou.ktp_data.entity.Course;
import com.wangyou.ktp_data.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private HomeworkDao homeworkDao;

    @Override
    public String createCourse(Course course, String uid) {
        course.setCid(System.currentTimeMillis() + "");
        String inviteCode = UUID.randomUUID().toString().substring(0, 6);
        course.setInviteCode(inviteCode);
        Integer result = courseDao.createCourse(course);
        if (result == 1) {
            result += courseDao.bindUser(System.currentTimeMillis() + "", uid, course.getCid());
        }
        return result > 1 ? "successful" : "unsuccessful";
    }

    @Override
    public String updateCourseInfo(Course course) {
        Integer result = courseDao.updateCourseInfo(course);
        messageDao.updateCourse(course.getTitle(), course.getCid());
        return result == 1 ? "successful" : "unsuccessful";
    }

    @Override
    public String joinCourse(String inviteCode, String uid) {
        String cid = courseDao.findCourseIdByInviteCode(inviteCode)[0];
        Integer result = 0;
        Course course = courseDao.checkJoinState(uid, cid);
        if (course == null) {
            // 初次加入
            result += courseDao.joinCourse(System.currentTimeMillis() + "", uid, cid);
        } else {
            result += courseDao.reJoinCourse(uid, cid);
        }
        course = courseDao.checkJoinState(uid, cid);
        // 如果加入的是学生
        if (course.getRole().equals("学")) {
            // 课程总人数加一
            result += courseDao.increaseTotal(cid);
            // 作业的未提交人数加一
            result += homeworkDao.joinCourse(cid);
            // 关联当前课程的所有消息
            result += messageDao.joinCourse(cid,uid);
        }
        return result > 1 ? "successful" : "unsuccessful";
    }

    @Override
    public Integer setTop(String uid, String cid) {
        return courseDao.setTop(uid, cid);
    }

    @Override
    public Integer cancelTop(String uid, String cid) {
        return courseDao.cancelTop(uid, cid);
    }

    @Override
    public Integer setReorganize(String uid, String cid) {
        return courseDao.setReorganize(uid, cid);
    }

    @Override
    public Integer cancelReorganize(String uid, String cid) {
        return courseDao.cancelReorganize(uid, cid);
    }

    @Override
    public Integer setQuit(String uid, String cid) {
        Integer result = courseDao.setQuit(uid, cid);
        Course course = courseDao.checkJoinState(uid,cid);
        if (course.getRole().equals("学")){
            result += courseDao.decreaseTotal(cid);
            result += homeworkDao.quitCourse(cid);
        }
        return result;
    }

    @Override
    public List<Course> findCoursesByUid(String uid) {
        List<Course> courses = courseDao.findCoursesByUid(uid);
        for (Course course:courses) {
            course.setHomeworks(homeworkDao.findLastHomeworkByCid(course.getCid()));
        }
        return courses;
    }

    @Override
    public Course findCourseByCidAndUid(String cid, String uid) {
        return courseDao.findCourseByCidAndUid(cid, uid);
    }
}
