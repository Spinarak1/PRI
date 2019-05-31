import * as types from '../types';

const userProfile = {
  state: {
    records: [],
    userRate: [],
    userReviews: []
  },
  getters: {
      [types.OWNED_RECORDS]: state => {
      return state.records;
    },
    [types.GET_RATING]:(state, {recordId}) => {
      return state.userRate;
    }
  },
  mutations: {
    [types.ADD_RECORD]: (state, {recordId, name, released, rating}) => {
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
    },  // 04.06.2019 godzina 19:00
    // recordId, name, released, rating
    [types.SET_RATING]: (state, {recordId, name, released, rating}) => {
      /*const userRating = state.userRate.find(element => element.id == recordId);
      if(!userRating) {
        state.userRate.push({
          id: recordId,
          name: name,
          released: released,
          rating: rating
        })
      } else {
        alert("huj ci kurwa w dupe")
      }*/
    },
    [types.DEL_ALBUM]: (state, {recordId, name, rating}) => {
      const record = state.records.find(element => element.id === recordId);
      if(record) {
        state.records.splice(state.records.indexOf(record), 1);
      }
    },
  },
  actions: {
    [types.ADD_TO_OWNED]: ({commit}, album) => {
      commit(types.ADD_RECORD, album);
    },
    [types.USER_RATE]: ({commit}, userRate) => {
      commit(types.SET_RATING, userRate)
    },
    /*setReviews({commit}, review) {
      commit('setReviews', review)
    } */
  }
};

export default userProfile;
