
const user = {
  state: {
    records: [],
    recordRate: null
  },
  getters: {
    recordsShow(state) {
      return state.records;
    }
  },
  mutations: {
    addRecord(state, {recordId, name, released, rating} ) {
      const record = state.records.find(element => element.id == recordId);
      if(!record) {
        state.records.push({
          id: recordId,
          name: name,
          released: released,
          rating: rating
        })
      } else {
        alert('This record is already on your list!');
      }
      /*const record = state.records.find(element => {
        console.log(element);

      });

      /*if(record) {
        console.log('You\'ve already own this album');
      }
      state.records.push(album);*/
    },
    removeRecord(state, {recordId, name, rating}) {
      const record = state.records.find(element => element.id === recordId);
      if(record) {
        state.records.splice(state.records.indexOf(record), 1);
      }
    },
    setRating(state, album) {

    }
  },
  actions: {
    addRecords({commit}, album) {
      commit('addRecord', album);
    },
    setRatings({commit}, album) {
      commit()
    }
  }
};

export default user;
