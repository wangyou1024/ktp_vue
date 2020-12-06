package com.wangyou.ktp_data.service;

import com.wangyou.ktp_data.entity.Course;

import java.util.List;

public interface CourseService {
    /**
     * 创建课程
     * @param course: {cid: null, title: something, className: something,
     *              inviteCode: null, semester: something, term: 0/1,
     *              teacherHeader: something, teacherName: something,
     *              total:0}
     * @param uid
     * @return
     */
    String createCourse(Course course, String uid);

    // 更新课程信息
    String updateCourseInfo(Course course);

    // 加入课程
    String joinCourse(String inviteCode, String uid);

    // 置顶
    Integer setTop(String uid, String cid);

    Integer cancelTop(String uid, String cid);

    // 归档
    Integer setReorganize(String uid, String cid);

    Integer cancelReorganize(String uid, String cid);

    // 退出
    Integer setQuit(String uid, String cid);

    // 查寻某一用户的所有课程
    List<Course> findCoursesByUid(String uid);

    // 根据id查找
    Course findCourseByCidAndUid(String cid,String uid);

}
