
const user = {
  state: {
    records: [],
    userRate: [],
    userReviews: []
  },
  getters: {
    recordsShow(state) {
      return state.records;
    },
    getRating(state, {recordId}) {
      return state.userRate;
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
    },
    // recordId, name, released, rating
    setRating(state, {recordId, name, released, rating}) {
      state.userRate.push({
        id: recordId,
        name: name,
        released: released,
        rating: rating
      })
    },
    removeRecord(state, {recordId, name, rating}) {
      const record = state.records.find(element => element.id === recordId);
      if(record) {
        state.records.splice(state.records.indexOf(record), 1);
      }
    },
  },
  actions: {
    addRecords({commit}, album) {
      commit('addRecord', album);
    },
    setRatings({commit}, userRate) {
      commit('setRating', userRate)
    },
    /*setReviews({commit}, review) {
      commit('setReviews', review)
    } */
  }
};

export default user;
