const user = {
  state: {
    records: []
  },
  getters: {

  },
  mutations: {
    addRecord(state, {recordId, name, rating}) {
      const record = state.records.find(element => element.id == recordId);
      if(!record) {
        state.records.push({
          id: recordId,
          name: name,
          rating: rating
        })
      }
    },
    removeRecord(state, {recordId, name, rating}) {
      const record = state.records.find(element => element.id == recordId);
      if(record) {
        state.records.splice(state.records.indexOf(record), 1);
      }
    }
  },
  actions: {
    //addRecord({commit})
  }
};

export default {
  user
}
