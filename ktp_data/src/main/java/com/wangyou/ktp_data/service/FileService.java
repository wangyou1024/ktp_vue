package com.wangyou.ktp_data.service;

import com.wangyou.ktp_data.entity.FileData;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FileService {
    /**
     * @param file
     * @param fileData:{fid:null,aid: something, hid: something, name:null, realName:null, classify:0/1}
     * @param request
     * @return
     */
    FileData uploadFile(MultipartFile file, FileData fileData, HttpServletRequest request);
    void downloadFile(String fid, HttpServletResponse response);
    String deleteFile(String fid);

}
