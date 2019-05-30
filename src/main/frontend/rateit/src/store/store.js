import Vue from 'vue';
import Vuex from 'vuex';

import records from './modules/records';
import users from './modules/users'
import userProfile from './modules/userProfile'

Vue.use(Vuex);
Vue.config.devtools = true;

export const store = new Vuex.Store({
  modules: {
    records,
    users,
    user: userProfile
  }
});
