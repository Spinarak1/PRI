import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'

import SignIn from './views/auth/SignIn'
import SignUp from './views/auth/SignUp'

import Dashboard from './views/dashboard/Dashboard'
import User from './views/user/User'
import Ranking from './views/dashboard/Ranking'

import Test from './views/user/Test'

import AdminDashboard from './views/admin/AdminDashboard'

Vue.use(Router)

export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
        {
            path: '/',
            name: 'home',
            component: Home
        },
        {
            path: '/signin',
            name: 'signin',
            component: SignIn
        },

        {
            path: '/signup',
            name: 'signup',
            component: SignUp
        },

        {
            path: '/dashboard',
            name: 'dashboard',
            component: Dashboard
        },


        {
            path: '/user',
            name: 'user',
            component: User
        },

        {
          path: '/ranking',
          name: 'ranking',
          component: Ranking
        },

        {
            path: '/admindashboard',
            name: 'admindashboard',
            component: AdminDashboard
        },

        {
            path: '/test',
            name: 'testls',
            component: Test
        },
    ]
})
