import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store/store'
import vuetify from './plugins/vuetify';
import Vuetify from 'vuetify'

import axios from 'axios'

Vue.use(Vuetify)
Vue.config.productionTip = false

axios.defaults.baseURL = 'http://localhost:8080';

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
