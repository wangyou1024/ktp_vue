package com.wangyou.ktp_data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Course {
    private String cid;
    // 课程名称
    private String title;
    // 班级名称
    private String className;
    // 邀请码
    private String inviteCode;
    // 学年
    private String semester;
    // 学期
    private Integer term;
    // 老师头像
    private String teacherHeader;
    // 老师名称
    private String teacherName;
    // 课程人数
    private Integer total;
    // 角色：教/学
    private String role;
    // 是否置顶
    private Integer top;
    // 是否归档
    private Integer reorganize;
    // 是否退课
    private Integer quit;
    // 首页展示的两个作业
    private List<Homework> homeworks;
}
