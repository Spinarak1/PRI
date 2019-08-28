import axios from 'axios'

const state = {
    user: '',
    albums: [],
    ratedAlbums: [],
};

const getters = {
    getUser: state => {
        return state.user;
    },

    getUserID: state => {
        return state.user.id;
    },

    getAlbums: state => {
        return state.albums;
    },

    getRated: state => {
        return state.ratedAlbums;
    }
};

const mutations = {
    activeUser: (state, payload) => {
        state.user = payload
    },

    albumInfo: (state, payload) => {
        const album = state.albums.find(element => element.id === payload.id);
        if(!album) {
            state.albums.push(payload);
            alert('Album has been added');
        } else alert('The album is already on your list');
    },

    ratedAlbum: (state, payload) => {
        const album = state.ratedAlbums.find(element => element.id === payload.id);
        if(!album) {
            state.ratedAlbums.push(payload);
            alert('Album has been added to rated list');
        } else alert('The album is already on your rated list');
    },

    logOut: (state) => {
        state.user = '';
        state.albums.length = 0;
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