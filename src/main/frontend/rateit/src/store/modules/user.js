import axios from 'axios'

const state = {
    user: '',
    albums: []
};

const getters = {
    getUser: state => {
        return state.user;
    },

    getUserID: state => {
        return state.user.id;
    }
};

const mutations = {
    activeUser: (state, payload) => {
        state.user = payload
    },

    albumInfo: (state, payload) => {
        state.albums.push(payload);
    },

    logOut: (state) => {
        state.user = '';
    }
};

const actions = {
    signIn: ({commit}, payload) => {
        commit('activeUser', payload);
    },
};

export default  {
    state, getters, mutations, actions
}