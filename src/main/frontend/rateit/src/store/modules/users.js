import axios from '../../axios-auth'

const users = {
  state: {
      users: []
  },
  getters: {
    showUsers(state) {
      return state.users;
    }
  },
  mutations: {
    setUsers(state, users) {
      state.users = users;
    }
  },
  actions: {
    fetchUsers({commit}) {
      axios.get('/api/users')
        .then(resp => {
          commit('setUsers', resp.data);
        })
    }
  }
};

export default users;
