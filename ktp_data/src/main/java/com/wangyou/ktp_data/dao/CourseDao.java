package com.wangyou.ktp_data.dao;

import com.wangyou.ktp_data.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "CourseDao")
public interface CourseDao {
    // 创建课程
    Integer createCourse(Course course);

    Integer bindUser(@Param("ucid") String ucid, @Param("uid") String uid, @Param("cid") String cid);

    // 教师信息修改时要同时更新课程里面的教师相关内容
    Integer bindTeacherHeader(String teacherHeader, String uid);

    Integer bindTeacherName(String teacherName, String uid);

    // 更新课程
    Integer updateCourseInfo(Course course);

    // 加入课程
    String[] findCourseIdByInviteCode(String inviteCode);

    // 初次加入课程与重复加入课程需要进行不同的处理
    Course checkJoinState(String uid, String cid);

    // 默认加入为学生，置顶，未归档，未退出
    Integer joinCourse(String ucid, String uid, String cid);

    // 重新加入直接将退出标记位归0
    Integer reJoinCourse(String uid, String cid);

    // 更新总人数
    Integer increaseTotal(String cid);

    // 置顶
    Integer setTop(String uid, String cid);

    Integer cancelTop(String uid, String cid);

    // 归档
    Integer setReorganize(String uid, String cid);

    Integer cancelReorganize(String uid, String cid);

    // 退出
    Integer setQuit(String uid, String cid);

    Integer decreaseTotal(String cid);

    // 查寻某一用户的所有课程
    List<Course> findCoursesByUid(String uid);
    // 根据cid查询
    Course findCourseByCidAndUid(String cid, String uid);
}
