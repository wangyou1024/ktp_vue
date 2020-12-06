package com.wangyou.ktp_data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class FileData {
    private String fid;
    private String aid;
    private String hid;
    // 文件原名称
    private String name;
    // 文件在服务器上的名称
    private String realName;
    // 文件的类别：题目中的或者是作业中的
    private Integer classify;

}
