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
    [types.SET_IMAGES]: (state, payload) => {
      state.images = payload;
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
      axios.get('http://www.splashbase.co/api/v1/images/search?query=tree')
        .then(resp => {
          console.log(resp);
          commit(types.SET_IMAGES, resp);
        })
        .catch(error => console.log(error));
    }
};

export default records;
