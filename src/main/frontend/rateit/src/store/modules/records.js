import axios from "../../axios-auth";

const records = {
  state: {
    records: []
  },
  getters: {
    showRecords(state) {
      //console.log("Getters :) AUUUUU");
      return state.records;
    }
  },
  mutations: {
    setAlbums(state, albums) {
      state.records = albums;
      //console.log(`Mutationes ${state.records.length}`)
    }
  },
  actions: {
    fetchAlbums({commit}) {
      axios.get('api/cds')
        .then(resp => {
          //console.log(`Pozdro z Vuex ;) ${resp.data[0].name}`);
          commit('setAlbums', resp.data);
        })
    }
  }
};

export default records;
