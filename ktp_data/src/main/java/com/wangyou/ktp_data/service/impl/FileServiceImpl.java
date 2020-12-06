package com.wangyou.ktp_data.service.impl;

import com.wangyou.ktp_data.dao.FileDao;
import com.wangyou.ktp_data.entity.FileData;
import com.wangyou.ktp_data.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;

@Service
public class FileServiceImpl implements FileService {

    private String headerPath = "E:\\javaEE\\finaly\\file_upload\\work\\";

    @Autowired
    private FileDao fileDao;

    @Override
    public FileData uploadFile(MultipartFile file, FileData fileData, HttpServletRequest request) {
        String newName = "";
        try {
            String oldName = file.getOriginalFilename();
            newName = System.currentTimeMillis() + oldName.substring(oldName.lastIndexOf("."));
            fileData.setRealName(newName);
            fileData.setName(oldName);
            fileData.setFid(System.currentTimeMillis() + "");
            fileDao.uploadFile(fileData);
            if (fileData.getAid() != null) {
                // 答案的附件
                ArrayList<String> fids = new ArrayList<>();
                fids.add(fileData.getFid());
                fileDao.bindAid(fileData.getHid(), fileData.getAid(), fids);
            } else if (fileData.getHid() != null) {
                // 题目的附件
                ArrayList<String> fids = new ArrayList<>();
                fids.add(fileData.getFid());
                fileDao.bindHid(fileData.getHid(), fids);
            } else {
                // 新上件文件
            }
            File folder = new File(headerPath, newName);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            file.transferTo(folder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileData;
    }
    @Override
    public void downloadFile(String fid, HttpServletResponse response) {
        FileInputStream inputStream = null;
        OutputStream outputStream = null;
        // 默认图片
        FileData fileData = fileDao.findFileByFid(fid);
        try {
            inputStream = new FileInputStream(headerPath + fileData.getRealName());
            Integer i = inputStream.available();
            byte[] buff = new byte[i];
            inputStream.read(buff);
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileData.getName(), "utf-8"));
            outputStream = response.getOutputStream();
            outputStream.write(buff);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String deleteFile(String fid) {
        FileData fileData = fileDao.findFileByFid(fid);
        boolean deleted = new File(headerPath, fileData.getRealName()).delete();
        if (deleted) {
            fileDao.deleteFile(fid);
            return "success";
        }
        return "unsuccessful";
    }
}
