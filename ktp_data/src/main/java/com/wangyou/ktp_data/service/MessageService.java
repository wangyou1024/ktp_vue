package com.wangyou.ktp_data.service;

import com.wangyou.ktp_data.entity.Message;

import java.util.List;

public interface MessageService {
    String bindCall(String hid, String uid);
    String readMessage(String mid, String uid);
    String readAllMessages(String uid);
    List<Message> findMessagesByUid(String uid);
}
