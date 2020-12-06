package com.wangyou.ktp_data.controller;

import com.wangyou.ktp_data.entity.Homework;
import com.wangyou.ktp_data.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/homework")
@RestController
@CrossOrigin
public class HomeworkController {

    @Autowired
    private HomeworkService homeworkService;

    @PostMapping("/createHomework")
    public @ResponseBody
    Homework createHomework(@RequestBody Homework homework) {
        return homeworkService.createHomework(homework);
    }

    @PostMapping("/updateHomework")
    public @ResponseBody
    String updateHomework(@RequestBody Homework homework) {
        return homeworkService.updateHomework(homework);
    }

    @PostMapping("/quitHomework")
    public @ResponseBody
    String quitHomework(@RequestBody Map<String,Object> data) {
        return homeworkService.quitHomework(data.get("hid").toString());
    }

    @GetMapping("/findHomeworksByCid")
    public @ResponseBody
    List<Homework> findHomeworksByCid(@RequestParam String cid) {
        return homeworkService.findHomeworksByCid(cid);
    }

    @GetMapping("/findHomeworkByHid")
    public @ResponseBody
    Homework findHomeworkByHid(@RequestParam String hid) {
        return homeworkService.findHomeworkByHid(hid);
    }
}
