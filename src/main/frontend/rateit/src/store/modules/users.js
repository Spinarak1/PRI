import axios from '../../axios-auth'
import * as types from '../types'
const users = {
  state: {
      users: []
  },
  getters: {
    [types.SHOW_USERS]: state => {
      return state.users;
    }
  },
  mutations: {
    [types.SET_USERS]: (state, users) => {
      state.users = users;
    }
  },
  actions: {
    [types.FETCH_USERS]: ({commit}) => {
      axios.get('/api/users')
        .then(resp => {
          commit(types.SET_USERS, resp.data);
        })
    }
  }
};

export default users;
