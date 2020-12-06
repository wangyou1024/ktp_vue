package com.wangyou.ktp_data.service.impl;

import com.wangyou.ktp_data.dao.MessageDao;
import com.wangyou.ktp_data.entity.Message;
import com.wangyou.ktp_data.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Override
    public String bindCall(String hid, String uid) {
        Integer result = messageDao.bindCall(hid, uid);
        return result > 0?"successful":"unsuccessful";
    }

    @Override
    public String readMessage(String mid, String uid) {
        Integer result = messageDao.readMessage(mid, uid);
        return result>0?"successful":"unsuccessful";
    }

    @Override
    public String readAllMessages(String uid) {
        Integer result = messageDao.readAllMessages(uid);
        return result>0?"successful":"unsuccessful";
    }

    @Override
    public List<Message> findMessagesByUid(String uid) {
        return messageDao.findMessagesByUid(uid);
    }
}
