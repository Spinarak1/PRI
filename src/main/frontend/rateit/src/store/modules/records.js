import axios from "../../axios-auth";
import * as types from '../types';

const records = {
  state: {
    records: []
  },
  getters: {
    [types.SHOW_RECORDS]: state => {
      return state.records;
    }
  },
  mutations: {
    [types.SET_ALBUMS]: (state, albums) => {
      state.records = albums;
      //console.log(`Mutationes ${state.records.length}`)
    }
  },
  actions: {
    [types.FETCH_ALBUMS]: ({commit}) => {
      axios.get('api/cds')
        .then(resp => {
          //console.log(`Pozdro z Vuex ;) ${resp.data[0].name}`);
          commit(types.SET_ALBUMS, resp.data);
        })
    }
  }
};

export default records;
