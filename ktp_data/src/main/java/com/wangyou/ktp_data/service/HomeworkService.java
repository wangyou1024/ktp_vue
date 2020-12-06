package com.wangyou.ktp_data.service;

import com.wangyou.ktp_data.entity.Homework;

import java.util.List;

public interface HomeworkService {
    /**
     * 创建作业
     * @param homework: {hid:null, cid: something, title: something,
     *                content: something, startDate: something, startTime: something,
     *                overDate: something, overTime: something, corrected: 0,
     *                correcting: 0, submit: total, quit: 0}
     * @return
     */
    Homework createHomework(Homework homework);
    // 更新作业信息
    String updateHomework(Homework homework);
    // 删除作业
    String quitHomework(String hid);
    // 查询课程作业
    List<Homework> findHomeworksByCid(String cid);
    // 查询某一作业
    Homework findHomeworkByHid(String hid);
}
