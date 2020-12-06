package com.wangyou.ktp_data.service.impl;

import com.wangyou.ktp_data.dao.AnswerDao;
import com.wangyou.ktp_data.dao.FileDao;
import com.wangyou.ktp_data.dao.HomeworkDao;
import com.wangyou.ktp_data.entity.Answer;
import com.wangyou.ktp_data.entity.FileData;
import com.wangyou.ktp_data.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerDao answerDao;

    @Autowired
    private HomeworkDao homeworkDao;

    @Autowired
    private FileDao fileDao;

    @Override
    public String submitAnswer(Answer answer) {
        answer.setAid(System.currentTimeMillis() + "");
        Integer result = answerDao.submitAnswer(answer);
        List<String> fids = new ArrayList<>();
        // sql语句存在缺陷，需要添加一个默认值
        fids.add("0000");
        for (FileData fileData : answer.getFileList()) {
            fids.add(fileData.getFid());
        }
        result += fileDao.bindAid(answer.getHid(), answer.getAid(), fids);
        // 提交答案后同步作业数据
        result += homeworkDao.submitHomework(answer.getHid());
        return result > 0 ? "successful" : "unsuccessful";
    }

    @Override
    public String updateContent(String content, String aid) {
        Integer result = answerDao.updateContent(content, aid);
        return result > 0 ? "successful" : "unsuccessful";
    }

    @Override
    public String correctAnswer(String hid, Integer grade, String aid) {
        Answer answer = answerDao.findAnswerByAid(aid);
        Integer result = answerDao.correctAnswer(grade, aid);
        if (answer.getGrade() == -1) {
            // 之前没有批改，批改后的作业数据同步
            result += homeworkDao.correctHomework(hid);
        }
        return result > 0 ? "successful" : "unsuccessful";
    }

    @Override
    public List<Answer> findAnswersByHid(String hid) {
        return answerDao.findAnswersByHid(hid);
    }

    @Override
    public Answer findAnswerByAid(String aid) {
        return answerDao.findAnswerByAid(aid);
    }

    @Override
    public Answer findAnswerByUidAndHid(String uid, String hid) {
        return answerDao.findAnswerByUidAndHid(uid, hid);
    }
}
