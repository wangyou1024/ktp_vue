package com.wangyou.ktp_data.controller;

import com.wangyou.ktp_data.entity.Message;
import com.wangyou.ktp_data.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/message")
@RestController
@CrossOrigin
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/bindCall")
    public @ResponseBody String bindCall(@RequestBody Map<String,Object> data){
        return messageService.bindCall(data.get("hid").toString(),data.get("uid").toString());
    }

    @PostMapping("/readMessage")
    public @ResponseBody String readMessage(@RequestBody Map<String,Object> data){
        return messageService.readMessage(data.get("mid").toString(),data.get("uid").toString());
    }

    @PostMapping("/readAllMessages")
    public @ResponseBody String readAllMessages(@RequestBody Map<String,Object> data){
        return messageService.readAllMessages(data.get("uid").toString());
    }

    @GetMapping("/findMessagesByUid")
    public @ResponseBody
    List<Message> findMessagesByUid(@RequestParam String uid){
        return messageService.findMessagesByUid(uid);
    }



}
