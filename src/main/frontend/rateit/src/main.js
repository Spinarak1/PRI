import Vue from 'vue'
import VueRouter from 'vue-router'
import VueResource from 'vue-resource'
import App from './App.vue'
import { routes } from './routes'
import axios from 'axios'
import Vuelidate from 'vuelidate'

import { store } from './store/store'

Vue.use(VueRouter);
Vue.use(VueResource);
Vue.use(Vuelidate);

/*
export const AXIOS = axios.create({
  baseURL: 'http://localhost:8080',
  headers: {
    'Access-Control-Allow-Origin': 'http://localhost:8081'
  }
}); */

export const eventBus = new Vue();


axios.defaults.baseURL = 'http://localhost:8080';
//axios.defaults.headers.common['Access-Control-Allow-Origin'] = 'http://localhost:8080';
axios.defaults.headers = {
  'Access-Control-Allow-Origin': '*'

};

export const router = new VueRouter({
  mode: 'history',
  routes
});

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
