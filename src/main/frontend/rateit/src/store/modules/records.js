import axios from "../../axios-auth";
import * as types from '../types';

const records = {
  state: {
    records: [],
    images: [],
    ranking: []
  },
  getters: {
    [types.SHOW_RECORDS]: state => {
      return state.records;
    },
    [types.SHOW_IMAGES]: state => {
      return state.images;
    },
    [types.SHOW_RANKING]: state => {
      return state.ranking;
    }
  },
  mutations: {
    [types.SET_ALBUMS]: (state, albums) => {
      state.records = albums;
      //console.log(`Mutationes ${state.records.length}`)
    },
    [types.SET_IMAGES]: (state, data) => {
      state.images = data;
    },
    [types.SET_RANKING]: (state, payload) => {
      state.ranking = payload;
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
    },
  [types.RANKING]: ({commit}) => {
    axios.get('/api/cd/ratingList')
      .then(resp => {
        const ranking = resp.data;
        commit(types.SET_RANKING, ranking)
      })
      .catch(error => console.log(error));
  }
};

export default records;
