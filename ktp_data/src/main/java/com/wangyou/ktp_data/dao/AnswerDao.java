package com.wangyou.ktp_data.dao;

import com.wangyou.ktp_data.entity.Answer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "AnswerDao")
public interface AnswerDao {
    //提交作业，默认分数为-1
    Integer submitAnswer(Answer answer);
    // 更新作业提交的内容
    Integer updateContent(String content,String aid);
    // 批改作业
    Integer correctAnswer(Integer grade,String aid);
    // 学生更新了信息
    Integer updateUserInfo(String number,String name,String uid);
    // 查询某一课程的所有作业答案
    List<Answer> findAnswersByHid(String hid);
    // 根据aid查询答案
    Answer findAnswerByAid(String aid);
    // 查询某人的作业答案
    Answer findAnswerByUidAndHid(String uid,String hid);
}
