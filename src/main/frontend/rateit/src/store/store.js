import Vue from 'vue';
import Vuex from 'vuex';

import axios from '../axios-auth';

Vue.use(Vuex);

export const store = new Vuex.Store({
  state: {
    records: [],
    users: []
  },

  getters: {

  },
  mutations: {

  },
  actions: {
    getRecords({commit}) {
      axios.get('/api/cds')
        .then(resp => {
          const data = resp.data;
          data.forEach(current => {
            console.log(current)
          })
        })
        .catch(error => console.log(error));
    },
    getUsers({commit}) {
      axios.get('/api/users')
        .then(resp => {
          const data = resp.data;
          data.forEach(current => {
            console.log(current)
          })
        })
        .catch(error => console.log(error));
    },
    signUp({commit}, authData) {
      axios.post('api/users', {
        email: authData.email,
        password: authData.password
      })
        .then(resp => {
          console.log(resp);
        })
        .catch(error => console.log(error));
    }
  }
});
