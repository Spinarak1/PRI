import WelcomePage from './components/welcome/welcome.vue'
import DashboardPage from './components/dashboard/dashboard.vue'
import SignupPage from './components/auth/signup.vue'
import SigninPage from './components/auth/signin.vue'
import User from './components/user/User.vue'
import Records from './components/records/Records.vue'
import Rates from './components/user/Rates.vue'
import Owned from './components/user/OwnedAlbums.vue'
import Reviews from './components/user/Reviews.vue'
import Wishlist from './components/user/Wishlist.vue'

export const routes = [
    { path: '/user', component: User },
    { path: '/records', component: Records },
    { path: '/rates', component: Rates },
    { path: '/owned', component: Owned },
    { path: '/reviews', component: Reviews },
    { path: '/wishlist', component: Wishlist },
    { path: '/', component: WelcomePage },
    { path: '/signup', component: SignupPage },
    { path: '/signin', component: SigninPage },
    { path: '/dashboard', component: DashboardPage }
];
