package com.wangyou.ktp_data.service;

import com.wangyou.ktp_data.entity.Answer;

import java.util.List;

public interface AnswerService {
    /**
     * 提交作业，默认分数为-1
     * @param answer:{aid: null, uid: something, hid: something, number: something,
     *              name: something, content: something, grade: -1, dateTime: something,
     *              fileList: [something]}
     * @return successful/unsuccessful
     */
    String submitAnswer(Answer answer);
    // 更新作业提交的内容
    String updateContent(String content,String aid);
    // 批改作业
    String correctAnswer(String hid,Integer grade,String aid);
    // 查询某一课程的所有作业答案
    List<Answer> findAnswersByHid(String hid);
    // 根据aid查询答案
    Answer findAnswerByAid(String aid);
    // 查询某人的作业答案
    Answer findAnswerByUidAndHid(String uid,String hid);
}
