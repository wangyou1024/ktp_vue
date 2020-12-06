package com.wangyou.ktp_data.dao;


import com.wangyou.ktp_data.entity.Homework;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "HomeworkDao")
public interface HomeworkDao {
    // 创建作业
    Integer createHomework(Homework homework);
    // 更新作业信息
    Integer updateHomework(Homework homework);
    // 删除作业
    Integer quitHomework(String hid);
    // 批改作业
    Integer correctHomework(String hid);
    // 提交作业，待批改人数加一，待提交人数减一
    Integer submitHomework(String hid);
    // 加入课程，待提交人数加一
    Integer joinCourse(String cid);
    // 退出课程，待提交人数减一
    Integer quitCourse(String cid);
    // 查询作业
    List<Homework> findHomeworksByCid(String cid);
    // 查询某一作业
    Homework findHomeworkByHid(String hid);
    // 查询每个课程前两个作业
    List<Homework> findLastHomeworkByCid(String cid);
}
