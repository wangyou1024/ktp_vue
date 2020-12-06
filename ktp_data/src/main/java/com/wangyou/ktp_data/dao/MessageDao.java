package com.wangyou.ktp_data.dao;

import com.wangyou.ktp_data.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "MessageDao")
public interface MessageDao {
    // 创建作业时生成“新作业”/“催交”消息
    Integer createMessage(Message message);

    // "新作业"消息绑定所有课程学生
    Integer bindNew(String mid, String cid);

    // 有新人加入课程时，给他绑定所有新作业消息
    Integer joinCourse(String cid, String uid);

    // "催交"消息绑定
    Integer bindCall(String hid, String uid);

    // 课程名称变化时
    Integer updateCourse(String course, String cid);

    // 作业名称变化
    Integer updateTitle(String title, String hid);

    // 读消息
    Integer readMessage(String mid, String uid);

    // 读所有消息
    Integer readAllMessages(String uid);

    // 查询某人的全部消息
    List<Message> findMessagesByUid(String uid);
}
