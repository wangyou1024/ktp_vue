package com.wangyou.ktp_data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Answer {
    private String aid;
    private String uid;
    private String hid;
    // 学号：重复字断，减少连表的频繁
    private String number;
    // 姓名
    private String name;
    // 内容
    private String content;
    // 成绩
    private Integer grade;
    // 提交时间
    private String dateTime;
    // 答案中的文件
    private List<FileData> fileList;
}
