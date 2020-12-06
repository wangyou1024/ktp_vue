import Vue from 'vue'
import Vuex from 'vuex'
import {Message} from "element-ui";
import router from "@/router";
import {postKeyValueRequest} from "../config/interceptor";
import axios from "axios";

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        BASE_URL: "/api",
        count: 0,
        user: {
            uid: "",
            username: "12345678901",
            password: "123456",
            name: "李明",
            school: "重庆理工大学",
            number: "11823020220",
            header: null,
        },
        messages: [{
            mid: "1",
            cid: "1",
            course: "算法分析与设计",
            classify: 0,
            hid: "1",
            title: "作业3",
            isRead: 0,
            publish: "2020-09-20"
        },
            {
                mid: "2",
                cid: "1",
                course: "算法分析与设计",
                classify: 1,
                hid: "2",
                title: "作业4",
                isRead: 0,
                publish: "2020-09-20"
            },],
        courses: [{
            cid: "1", title: "算法分析与设计",
            className: "118230201-02",
            inviteCode: "UFDKOW",
            semester: "2019-2020",
            term: 1,
            homeworks: [{hid: "1", title: "这是一个非常难的题，这是一个非常难的题"}, {hid: "2", title: "作业2"}],
            teacherHeader: null,
            teacherName: "陈艳",
            top: 1, reorganize: 0, quit: 0, role: "教", total: 5
        }
        ],
        detailCourse: {
            cid: "3", title: "Java EE",
            className: "118230201-02",
            inviteCode: "UFDKOW",
            role: "学", total: 5
        },
        homeworks: [
            {
                hid: "0", cid: "0", title: "实验一",
                content: "请大家提交实验报告：（1）插入排序与合并排序运行时间与排序元素个数的统计比较（2）阐述比较结果。（3） 利用算法采用的设计方法，时间复杂度等分析得到该结果的原因。" +
                    "（2）阐述比较结果。（3） 利用算法采用的设计方法，时间复杂度等分析得到该结果的原因。（2）阐述比较结果。" +
                    "（3） 利用算法采用的设计方法，时间复杂度等分析得到该结果的原因。（2）阐述比较结果。（3） " +
                    "利用算法采用的设计方法，时间复杂度等分析得到该结果的原因。",
                fileList: [{fid: '1', name: '实验一.doc'}],
                startDate: "2020-09-23",
                startTime: "22:00",
                overDate: "2020-12-19",
                overTime: "12:00",
                corrected: "89",
                correcting: "32",
                submit: "90",
            },
            {
                hid: "1", cid: "0", title: "实验二",
                content: "请大家提交实验报告：（1）插入排序与合并排序运行时间与排序元素个数的统计比较（2）阐述比较结果。（3） 利用算法采用的设计方法，时间复杂度等分析得到该结果的原因。" +
                    "（2）阐述比较结果。（3） 利用算法采用的设计方法，时间复杂度等分析得到该结果的原因。（2）阐述比较结果。" +
                    "（3） 利用算法采用的设计方法，时间复杂度等分析得到该结果的原因。（2）阐述比较结果。（3） " +
                    "利用算法采用的设计方法，时间复杂度等分析得到该结果的原因。",
                fileList: [{fid: '1', name: '实验一.doc'}],
                startDate: "2020-09-23",
                startTime: "22:00",
                overDate: "2020-12-19",
                overTime: "12:00",
                corrected: "89",
                correcting: "32",
                submit: "90",
            },
        ],
        answers: [
            {
                aid: "1",
                uid: "1",
                hid: "1",
                number: "1234566543",
                name: "李明",
                dateTime: "2020-09-18 9:00",
                content: "哈哈",
                fileList: [{fid: "123", name: "答案.doc"}, {fid: "123", name: "答案.doc"}],
                grade: 88
            },
            {
                aid: "2",
                uid: "2",
                hid: "1",
                number: "2345678876",
                name: "王磊",
                dateTime: "2020-09-18 9:00",
                content: "哈哈",
                fileList: [{fid: "123", name: "答案.doc"}, {fid: "123", name: "答案.doc"}],
                grade: -1
            },
            {
                aid: "3",
                uid: "3",
                hid: "1",
                number: "2345678876",
                name: "王磊",
                dateTime: "",
                content: "",
                fileList: [],
                grade: null
            },
        ]
    },
    mutations: {
        add(state, n) {
            state.count += n;
        },
        reduce(state) {
            this.state.count--;
        },
        login(state, user) {
            this.state.user = user;
        },
        register(state, user) {
            this.state.user = user;
        },
        logout(state) {
            this.state.user = null;
        },
        updateUser(state, user) {
            this.state.user = user;
        },
        initCourses(state, courses) {
            this.state.courses = courses;
        },
        setTop(state, cid) {
            this.state.courses.forEach((value, index, array) => {
                if (value.cid === cid) {
                    value.top = value.top ? 0 : 1;
                }
            })
        },
        setReorganize(state, cid) {
            this.state.courses.forEach((value, index, array) => {
                if (value.cid === cid) {
                    value.reorganize = value.reorganize ? 0 : 1;
                }
            })
        },
        setQuit(state, cid) {
            let courseIndex = 0;
            this.state.courses.forEach((value, index, array) => {
                if (value.cid === cid) {
                    courseIndex = index;
                    value.quit = value.quit ? 0 : 1;
                }
            });
            this.state.courses.splice(courseIndex, 1);
        },
        initMessages(state, messages) {
            this.state.messages = messages;
        },
        setRead(state, mid) {
            let messageIndex = 0;
            this.state.messages.forEach((value, index, array) => {
                if (value.mid === mid) {
                    messageIndex = index;
                }
            });
            this.state.messages[messageIndex].isRead = 1;
        },
        setAllRead(state) {
            this.state.messages.forEach(value => {
                value.isRead = 1;
            })
            this.state.messages.splice(0, this.state.messages.length);
        },
        enterCourse(state, course) {
            this.state.courses.push(course);
        },
        createCourse(state, course) {
            this.state.courses.push(course);
        },
        setDetailCourse(state, course) {
            this.state.detailCourse = course;
        },
        initHomeworks(state, homeworks) {
            this.state.homeworks = homeworks;
        },
        createHomework(state, homework) {
            this.state.homeworks.unshift(homework);
        },
        editHomework(state, homework) {
            this.state.homeworks.forEach((value, index) => {
                if (value.hid) {
                    value.title = homework.title;
                    value.content = homework.content;
                    value.overDate = homework.overDate;
                    value.overTime = homework.overTime;
                }
            });
        },
        deleteHomework(state, hid) {
            for (let i = 0; i < this.state.homeworks.length; i++) {
                if (this.state.homeworks[i].hid === hid) {
                    this.state.homeworks.splice(i, 1);
                    break;
                }
            }
        },
        addHomeworkFile(state, data) {
            console.log(data.file)
            this.state.homeworks.forEach((value, index) => {
                if (value.hid === data.hid) {
                    value.fileList.unshift(data.file);
                }
            })
        },
        deleteHomeworkFile(state, data) {
            this.state.homeworks.forEach((homework, index) => {
                if (homework.hid === data.hid) {
                    homework.fileList.forEach((item, index) => {
                        if (item.fid === data.fid) {
                            homework.fileList.splice(index, 1);
                        }
                    })
                }
            })
        },
        initAnswers(state, answers) {
            this.state.answers = answers;
        },
        deleteAnswerFile(state, data) {
            this.state.answers.forEach((answer, index) => {
                if (answer.aid === data.aid) {
                    answer.fileList.forEach((item, index) => {
                        if (item.fid === data.fid) {
                            answer.fileList.splice(index, 1);
                        }
                    })
                }
            })
        },
        setGrade(state, data) {
            this.state.answers.forEach((answer, index) => {
                if (answer.aid === data.aid) {
                    answer.grade = data.grade;
                }
            })
        }
    },
    actions: {
        addAction(context, n) {
            context.commit("add", n)
        },
        reduceAction({commit}) {
            commit("reduce")
        },
        loginAction({commit}, user) {
            const newUser = JSON.parse(JSON.stringify(user));
            postKeyValueRequest(this.state.BASE_URL + "/doLogin", newUser).then((res) => {
                if (res.data.obj != null) {
                    Message({message: "登录成功", type: "success"});
                    window.sessionStorage.setItem("user", JSON.stringify(res.data.obj))
                    commit("login", res.data.obj);
                    let path = router.app.$route.query.redirect;
                    router.replace((path === '/' || path === undefined) ? "/" : path)
                }
            }).catch(error => {
                console.log(error);
            })
        },
        registerAction({commit}, user) {
            axios.post(this.state.BASE_URL + "/user/register", user).then(res => {
                Message({message: "注册成功", type: "success"});
                router.replace("/login");
            }).catch(error => {
                console.log(error);
            })
        },
        logoutAction({commit}) {
            axios.get(this.state.BASE_URL + "/logout").then((res) => {
                window.sessionStorage.removeItem("user");
                router.replace('/login');
                commit("logout");
                Message.success("账户已退出！")
            })
        },
        findUserAction({commit}) {
            let uid = JSON.parse(window.sessionStorage.getItem("user")).uid;
            axios.get(this.state.BASE_URL + "/user/findUserById", {params: {uid: uid}}).then(res => {
                commit("updateUser", res.data);
            }).catch(error => {
                console.log(error);
            })
        },
        updateUserAction({commit}, user) {
            axios.post(this.state.BASE_URL + "/user/updateInfo", user).then(res => {
                if (res.data === 1) {
                    commit("updateUser", user);
                    Message.success("修改成功");
                }
            }).catch(error => {
                console.log(error);
            })
        },
        initCoursesAction({commit}) {
            let uid = JSON.parse(window.sessionStorage.getItem("user")).uid;
            axios.get(this.state.BASE_URL + "/course/findCoursesByUid", {params: {"uid": uid}})
                .then(res => {
                    commit("initCourses", res.data);
                })
                .catch(error => {
                    console.log("initCoursesAction", error);
                });
        },
        initMessagesAction({commit}) {
            axios.get(this.state.BASE_URL + "/message/findMessagesByUid", {params: {'uid': JSON.parse(window.sessionStorage.getItem("user")).uid}})
                .then(res => {
                    commit("initMessages", res.data);
                })
                .catch(error => {
                    console.log("initMessagesAction", error);
                })
        },
        bindCall({dispatch,commit},data){
          axios.post(this.state.BASE_URL + "/message/bindCall",data)
              .then(res=>{
                  if (res.data === "successful"){
                     return dispatch("initMessagesAction");
                  }
              })
              .catch(error=>{
                  console.log("bindCall",error);
              })
        },
        setReadAction({commit}, mid) {
            axios.post(this.state.BASE_URL + "/message/readMessage", {'uid': this.state.user.uid, 'mid': mid})
                .then(res => {
                    if (res.data === "successful") {
                        commit("setRead", mid);
                    }
                })
                .catch(error => {
                    console.log("setReadAction", error);
                })
        },
        setAllReadAction({commit}) {
            axios.post(this.state.BASE_URL + "/message/readAllMessages", {'uid': this.state.user.uid})
                .then(res => {
                    if (res.data === "successful")
                        commit("setAllRead");
                })
                .catch(error => {
                    console.log("setReadAction", error);
                })
        },
        setTopAction({commit}, cid) {
            this.state.courses.forEach((value, index) => {
                if (value.cid === cid) {
                    let url = this.state.BASE_URL;
                    if (value.top === 0) {
                        url += "/course/setTop";
                    } else {
                        url += "/course/cancelTop";
                    }
                    axios.get(url,
                        {params: {"uid": this.state.user.uid, "cid": cid}})
                        .then(res => {
                            commit("setTop", cid);
                        })
                        .catch(error => {
                            console.log("setTopAction", error);
                        })
                }
            });

        },
        setReorganizeAction({commit}, cid) {
            this.state.courses.forEach((value, index) => {
                if (value.cid === cid) {
                    let url = this.state.BASE_URL;
                    if (value.reorganize === 0) {
                        url += "/course/setReorganize";
                    } else {
                        url += "/course/cancelReorganize";
                    }
                    axios.get(url,
                        {params: {"uid": this.state.user.uid, "cid": cid}})
                        .then(res => {
                            commit("setReorganize", cid);
                        })
                        .catch(error => {
                            console.log("setReorganizeAction", error);
                        });
                }
            });

        },
        setQuitAction({commit}, cid) {
            axios.get(this.state.BASE_URL + "/course/setQuit",
                {params: {"uid": this.state.user.uid, "cid": cid}})
                .then(res => {
                    commit("setQuit", cid);
                })
                .catch(error => {
                    console.log("setQuitAction", error);
                })
        },
        enterCourseAction({dispatch, commit}, inviteCode) {
            axios.get(this.state.BASE_URL + "/course/joinCourse",
                {params: {"inviteCode": inviteCode, "uid": this.state.user.uid}})
                .then(res => {
                    Message({message: res.data, type: "success"});
                    dispatch("initMessagesAction");
                    return dispatch("initCoursesAction");
                })
                .catch(error => {
                    console.log("enterCourseAction", error);
                })
        },
        createCourseAction({dispatch, commit}, part) {
            const course = {
                cid: (this.state.courses.length + 1) + "", title: part.title,
                className: part.className,
                inviteCode: "",
                semester: part.semester,
                term: part.term,
                role: "教",
                homeworks: [],
                teacherHeader: this.state.user.header,
                teacherName: this.state.user.name,
                top: 1, reorganize: 0, quit: 0, total: 0
            };
            axios.post(this.state.BASE_URL + "/course/createCourse", course,
                {params: {"uid": this.state.user.uid}})
                .then(res => {
                    Message({message: res.data, type: "success"});
                    return dispatch("initCoursesAction");
                })
                .catch(error => {
                    console.log("createCourseAction", error);
                })
        },
        setDetailCourseAction({commit}, cid) {
            axios.get(this.state.BASE_URL + "/course/findCourseByCidAndUid",
                {params: {"cid": cid,"uid": JSON.parse(window.sessionStorage.getItem("user")).uid}})
                .then(res => {
                    const course = res.data;
                    commit("setDetailCourse", course);
                })
                .catch(error => {
                    console.log("setDetailCourseAction", error);
                })
        },
        initHomeworkAction({commit}, cid) {
            axios.get(this.state.BASE_URL + "/homework/findHomeworksByCid",
                {params: {"cid": cid}})
                .then(res => {
                    commit("initHomeworks", res.data);
                })
                .catch(error => {
                    console.log("initHomeworkAction", error);
                })
        },
        createHomeworkAction({commit}, homework) {
            const temp = JSON.parse(JSON.stringify(homework));
            temp.cid = this.state.detailCourse.cid;
            temp.submit = this.state.detailCourse.total;
            axios.post(this.state.BASE_URL + "/homework/createHomework", temp)
                .then(res => {
                    commit("createHomework", res.data);
                })
                .catch(error => {
                    console.log("createHomeworkAction", error);
                })
        },
        editHomeworkAction({commit}, homework) {
            axios.post(this.state.BASE_URL + "/homework/updateHomework", homework)
                .then(res => {
                    if (res.data === "successful") {
                        Message.success("修改成功");
                        commit("editHomework", homework);
                    }
                })
                .catch(error => {
                    console.log("editHomeworkAction", error);
                })
        },
        deleteHomeworkAction({commit}, hid) {
            axios.post(this.state.BASE_URL + "/homework/quitHomework", {hid:hid})
                .then(res => {
                    if (res.data === "successful") {
                        Message.success("删除成功");
                        commit("deleteHomework", hid);
                    }
                })
                .catch(error => {
                    console.log("deleteHomeworkAction", error);
                })
        },
        addHomeworkFileAction({commit}, data) {
            commit("addHomeworkFile", data);
        },
        deleteHomeworkFileAction({commit}, data) {
            commit("deleteHomeworkFile", data);
        },
        initAnswersAction({commit}, hid) {
            axios.get(this.state.BASE_URL + "/answer/findAnswersByHid",
                {params: {"hid": hid}})
                .then(res => {
                    commit("initAnswers", res.data);
                })
                .catch(error => {
                    console.log("initAnswersAction", error);
                })
        },
        setGradeAction({commit}, answer) {
            axios.post(this.state.BASE_URL + "/answer/correctAnswer", answer)
                .then(res => {
                    if (res.data === "successful") {
                        commit("setGrade", answer);
                    }
                })
                .catch(error => {
                    console.log("setGradeAction", error);
                })
        }
    },
    modules: {}
})
