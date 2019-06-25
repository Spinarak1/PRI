import WelcomePage from './components/welcome/welcome.vue'
import DashboardPage from './components/dashboard/dashboard.vue'
import SignupPage from './components/auth/signup.vue'
import SigninPage from './components/auth/signin.vue'
import User from './components/user/User.vue'
  import UserStart from './components/user/UserStart'
import Records from './components/records/Records.vue'
import Rates from './components/user/Rates.vue'
import Owned from './components/user/OwnedAlbums.vue'
import Wishlist from './components/user/Wishlist.vue'
import AdminDashboard from './components/admin/AdminDashboard'
import AddAlbum from './components/admin/AddAlbum'
import AddUser from './components/admin/AddUser'
import ListOfUsers from './components/admin/ListOfUsers'
import Settings from './components/user/Settings'

export const routes = [
    { path: '/userProfile', component: User },
    { path: '/userstart', component: UserStart },
    { path: '/userProfile/rates', component: Rates },
    { path: '/userProfile/owned', component: Owned },
    { path: '/settings', component: Settings },
    { path: '/records', component: Records },
    { path: '/userProfile/wishlist', component: Wishlist },
    { path: '/', component: WelcomePage },
    { path: '/signup', component: SignupPage },
    { path: '/signin', component: SigninPage },
    { path: '/dashboard', component: DashboardPage },
    { path: '/admin/dashboard', component: AdminDashboard },
    { path: '/admin/addalbum', component: AddAlbum },
    { path: '/admin/adduser', component: AddUser },
    { path: '/admin/listofusers', component: ListOfUsers },
];
