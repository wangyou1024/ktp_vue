package com.wangyou.ktp_data.controller;

import com.wangyou.ktp_data.entity.Course;
import com.wangyou.ktp_data.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/course")
@RestController
@CrossOrigin
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("/createCourse")
    public @ResponseBody
    String createCourse(@RequestBody Course course, @RequestParam String uid) {
        return courseService.createCourse(course, uid);
    }

    @PostMapping("/updateCourseInfo")
    public @ResponseBody
    String updateCourseInfo(@RequestBody Course course) {
        return courseService.updateCourseInfo(course);
    }

    @GetMapping("/joinCourse")
    public @ResponseBody
    String joinCourse(@RequestParam String inviteCode, @RequestParam String uid) {
        return courseService.joinCourse(inviteCode, uid);
    }

    @GetMapping("/setTop")
    public @ResponseBody
    Integer setTop(String uid, String cid) {
        return courseService.setTop(uid, cid);
    }

    @GetMapping("/cancelTop")
    public @ResponseBody
    Integer cancelTop(String uid, String cid) {
        return courseService.cancelTop(uid, cid);
    }

    @GetMapping("/setReorganize")
    public @ResponseBody
    Integer setReorganize(String uid, String cid) {
        return courseService.setReorganize(uid, cid);
    }

    @GetMapping("/cancelReorganize")
    public @ResponseBody
    Integer cancelReorganize(String uid, String cid) {
        return courseService.cancelReorganize(uid, cid);
    }

    @GetMapping("/setQuit")
    public @ResponseBody
    Integer setOut(String uid, String cid) {
        return courseService.setQuit(uid, cid);
    }

    @GetMapping("/findCoursesByUid")
    public @ResponseBody
    List<Course> findCoursesByUid(String uid) {
        return courseService.findCoursesByUid(uid);
    }

    @GetMapping("/findCourseByCidAndUid")
    public @ResponseBody
    Course findCourseByCidAndUid(String cid, String uid) {
        return courseService.findCourseByCidAndUid(cid, uid);
    }

}
