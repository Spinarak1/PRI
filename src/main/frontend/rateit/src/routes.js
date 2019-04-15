import Home from './components/Home.vue'
import User from './components/user/User.vue'
import Records from './components/records/Records.vue'

export const routes = [
    { path: '/', component: Home },
    { path: '/user', component: User },
    { path: '/records', component: Records },
];