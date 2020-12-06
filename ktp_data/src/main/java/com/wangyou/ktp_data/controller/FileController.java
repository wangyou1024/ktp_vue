package com.wangyou.ktp_data.controller;

import com.wangyou.ktp_data.entity.FileData;
import com.wangyou.ktp_data.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/file")
@RestController
@CrossOrigin
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping("/uploadFile")
    public @ResponseBody
    FileData uploadFile(MultipartFile file, @RequestParam String hid, @RequestParam String aid, HttpServletRequest request) {
        FileData fileData = new FileData();
        if (aid.equals("null")) {
            fileData.setAid(null);
        }
        else {
            fileData.setAid(aid);
        }
        if (hid.equals("null")) {
            fileData.setHid(null);
        } else {
            fileData.setHid(hid);
        }
        return fileService.uploadFile(file, fileData, request);
    }

    @RequestMapping("/downloadFile")
    public void downloadFile(@RequestParam String fid, HttpServletResponse response) {
        fileService.downloadFile(fid, response);
    }

    @RequestMapping("/deleteFile")
    public @ResponseBody
    String deleteFile(@RequestParam("fid") String fid) {
        return fileService.deleteFile(fid);
    }
}
