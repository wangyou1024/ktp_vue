package com.wangyou.ktp_data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Homework {
    private String hid;
    private String cid;
    // 作业名称
    private String title;
    // 作业内容
    private String content;
    // 发布日期
    private String startDate;
    // 发布时间
    private String startTime;
    // 截止日期
    private String overDate;
    // 截止时间
    private String overTime;
    // 批改人数
    private Integer corrected;
    // 未批改人数
    private Integer correcting;
    // 未提交数
    private Integer submit;
    // 作业中的文件
    private List<FileData> fileList;
}
