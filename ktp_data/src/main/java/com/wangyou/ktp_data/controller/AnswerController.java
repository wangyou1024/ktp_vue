package com.wangyou.ktp_data.controller;

import com.wangyou.ktp_data.entity.Answer;
import com.wangyou.ktp_data.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/answer")
@RestController
@CrossOrigin
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping("/submitAnswer")
    public @ResponseBody String submitAnswer(@RequestBody Answer answer){
        return answerService.submitAnswer(answer);
    }

    @PostMapping("/updateContent")
    public @ResponseBody String updateContent(@RequestBody Answer answer){
        return answerService.updateContent(answer.getContent(), answer.getAid());
    }

    @PostMapping("/correctAnswer")
    public @ResponseBody String correctAnswer(@RequestBody Answer answer){
        return answerService.correctAnswer(answer.getHid(), answer.getGrade(), answer.getAid());
    }

    @GetMapping("/findAnswersByHid")
    public @ResponseBody
    List<Answer> findAnswersByHid(@RequestParam String hid){
        return answerService.findAnswersByHid(hid);
    }

    @GetMapping("/findAnswerByAid")
    public @ResponseBody Answer findAnswerByAid(String aid){
        return answerService.findAnswerByAid(aid);
    }

    @GetMapping("/findAnswerByUidAndHid")
    public @ResponseBody Answer findAnswerByUidAndHid(String uid, String hid){
        return answerService.findAnswerByUidAndHid(uid, hid);
    }
}
