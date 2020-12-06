package com.wangyou.ktp_data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Message {
    private String mid;
    // 前端跳转页面需要这个值，所以这里需要保留
    private String cid;
    // 同上
    private String hid;
    // 课程名称
    private String course;
    // 作业名称
    private String title;
    // 类别：发布新作业/催交作业
    private Integer classify;
    // 发送时间
    private String publish;
    // 是否已读
    private Integer isRead;
}
