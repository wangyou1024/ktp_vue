package com.wangyou.ktp_data.dao;

import com.wangyou.ktp_data.entity.FileData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "FileDao")
public interface FileDao {
    // 上传文件
    Integer uploadFile(FileData fileData);
    // 绑定作业题目文件
    Integer bindHid(String hid,List<String> fids);
    // 绑定作业答案文件
    Integer bindAid(String hid,String aid,List<String> fids);
    // 删除文件
    Integer deleteFile(String fid);
    // 查找作业题目对应的文件
    List<FileData> findProblemFiles(String hid);
    // 查找答案对应的文件
    List<FileData> findAnswerFiles(String aid);
    // 通过id查找
    FileData findFileByFid(String fid);
}
