package com.wangyou.ktp_data.service.impl;

import com.wangyou.ktp_data.dao.CourseDao;
import com.wangyou.ktp_data.dao.FileDao;
import com.wangyou.ktp_data.dao.HomeworkDao;
import com.wangyou.ktp_data.dao.MessageDao;
import com.wangyou.ktp_data.entity.Course;
import com.wangyou.ktp_data.entity.FileData;
import com.wangyou.ktp_data.entity.Homework;
import com.wangyou.ktp_data.entity.Message;
import com.wangyou.ktp_data.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeworkServiceImpl implements HomeworkService {

    @Autowired
    private HomeworkDao homeworkDao;

    @Autowired
    private FileDao fileDao;

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private CourseDao courseDao;

    @Override
    public Homework createHomework(Homework homework) {
        homework.setHid(System.currentTimeMillis() + "");
        Integer result = homeworkDao.createHomework(homework);
        List<FileData> fileDataList = homework.getFileList();
        List<String> fids = new ArrayList<>();
        fids.add("0000");
        for (FileData fileData:fileDataList) {
            fids.add(fileData.getFid());
        }
        // 绑定文件
        result += fileDao.bindHid(homework.getHid(),fids);
        // 生成新作业消息并绑定
        Message message = new Message();
        message.setMid(System.currentTimeMillis()+"");
        message.setCid(homework.getCid());
        message.setHid(homework.getHid());
        Course course = courseDao.findCourseByCidAndUid(homework.getCid(),"");
        message.setCourse(course.getTitle());
        message.setTitle(homework.getTitle());
        message.setClassify(0);
        message.setPublish(homework.getStartDate());
        result += messageDao.createMessage(message);
        result += messageDao.bindNew(message.getMid(), homework.getCid());
        // 生成催交消息
        message.setClassify(1);
        message.setMid((System.currentTimeMillis()+1)+"");
        result += messageDao.createMessage(message);
        return homework;
    }

    /**
     * @param homework:  {hid:somethind, cid: something, title: something,
     *                content: something, startDate: something, startTime: something,
     *                overDate: something, overTime: something, corrected: something,
     *                correcting: something, submit: something, quit: 0}
     * @return
     */
    @Override
    public String updateHomework(Homework homework) {
        Integer result = homeworkDao.updateHomework(homework);
        result += messageDao.updateTitle(homework.getTitle(),homework.getHid());
        return result>0?"successful":"unsuccessful";
    }

    @Override
    public String quitHomework(String hid) {
        Integer result = homeworkDao.quitHomework(hid);
        return result>0?"successful":"unsuccessful";
    }

    @Override
    public List<Homework> findHomeworksByCid(String cid) {
        List<Homework> result = homeworkDao.findHomeworksByCid(cid);
        return result;
    }

    @Override
    public Homework findHomeworkByHid(String hid) {
        Homework result = homeworkDao.findHomeworkByHid(hid);
        return result;
    }
}
