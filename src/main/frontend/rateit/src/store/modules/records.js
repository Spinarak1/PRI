import axios from "../../axios-auth";
import * as types from '../types';

const records = {
  state: {
    records: [],
    images: []
  },
  getters: {
    [types.SHOW_RECORDS]: state => {
      return state.records;
    },
    [types.SHOW_IMAGES]: state => {
      return state.images;
    }
  },
  mutations: {
    [types.SET_ALBUMS]: (state, albums) => {
      state.records = albums;
      //console.log(`Mutationes ${state.records.length}`)
    },
    [types.SET_IMAGES]: (state, data) => {
      state.images = data;
    }
  },
  actions: {
    [types.FETCH_ALBUMS]: ({commit}) => {
      axios.get('api/cds')
        .then(resp => {
          //console.log(`Pozdro z Vuex ;) ${resp.data[0].name}`);
          commit(types.SET_ALBUMS, resp.data);
        })
        .catch(error => console.log(error));
    }
  },
    [types.FETCH_IMAGE]: ({commit}) => {
      axios.get('/api/cds')
        .then(resp => {
          const data = resp.data;
          //console.log(data);
          commit(types.SET_IMAGES, data);
        })
        .catch(e => console.log(e));

    }
};

export default records;
