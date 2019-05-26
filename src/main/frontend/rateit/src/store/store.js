import Vue from 'vue';
import Vuex from 'vuex';
import records from './modules/records';
import users from './modules/users'
import user from './modules/user'

Vue.use(Vuex);
Vue.config.devtools = true;

export const store = new Vuex.Store({
  state: {

  },

  getters: {

  },
  mutations: {

  },
  actions: {

  },
  modules: {
    records,
    users,
    user
  }
});
