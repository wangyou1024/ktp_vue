import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from "@/views/Login";
import Register from "@/views/Register";
import Detail from "@/views/Detail";
import Correct from "@/views/Correct";
import SubmitHomework from "@/views/SubmitHomework";
import Person from "@/views/Person";
import Messages from "@/views/Messages";

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home,
    }, {
        path: '/login',
        name: 'Login',
        component: Login
    }, {
        path: '/register',
        name: 'Register',
        component: Register
    }, {
        path: '/detail/:id',
        name: 'Detail',
        component: Detail
    },{
    path: '/correct/:cid/:hid',
        name: 'Correct',
        component: Correct
    },
    {
        path: '/submitHomework/:cid/:hid',
        name: 'SubmitHomework',
        component: SubmitHomework
    },
    {
        path: '/person',
        name: 'Person',
        component: Person
    },
    {
        path: '/messages',
        name: 'Messages',
        component: Messages
    }
]

const router = new VueRouter({
    routes
})

export default router
