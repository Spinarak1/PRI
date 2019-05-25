
const user = {
  state: {
    records: []
  },
  getters: {
    recordsShow(state) {
      return state.records;
    }
  },
  mutations: {
    addRecord(state, /*{recordId, name, rating}*/ album) {
      /*const record = state.records.find(element => element.id == recordId);
      if(!record) {
        state.records.push({
          id: recordId,
          name: name,
          rating: rating
        })
      }*/
      state.records.push(album)
    },
    removeRecord(state, {recordId, name, rating}) {
      const record = state.records.find(element => element.id == recordId);
      if(record) {
        state.records.splice(state.records.indexOf(record), 1);
      }
    }
  },
  actions: {
    addRecords({commit}, album) {
      commit('addRecord', album);
    }
  }
};

export default user;
