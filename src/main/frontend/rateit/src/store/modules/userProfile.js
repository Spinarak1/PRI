import * as types from '../types';
import axios from '../../axios-auth';

const userProfile = {
  state: {
    records: [],
  },
  getters: {
    [types.OWNED_RECORDS]: state => {
      return state.records;
    },
    [types.GET_RATING]:(state, {recordId}) => {
        return state.records.find(element => element.id === recordId)
        // side note - don't know if it's gonna return boolean or a certain value
    },
    [types.GET_REVIEWS]: (state, review) => {
      return state.records.find(element => element.id === review.id); // TODO
    }
  },
  mutations: {
    // recordId, comment, name, ratingCount, sumOfRating, released
    [types.ADD_RECORD]: (state, {recordId, comment, name, ratingCount, sumOfRating, released}) => {
      const record = state.records.find(element => element.id === recordId);
      if(!record) {
        state.records.push({
          id: recordId,
          comment: comment,
          name: name,
          ratingCount: ratingCount,
          sumOfRating: sumOfRating,
          released: released
        })
      } else {
        alert('This record is already on your list!');
      }
    },  // 04.06.2019 godzina 19:00
    // recordId, name, released, rating
    [types.SET_RATING]: (state, {recordId, name, released, rating, sumOfRating, ratingCount}) => {
      /*state.userRate.push({
        id: recordId,
        name: name,
        released: released,
        rating: rating,
      }) */
      //console.log(state.records);
      const rate = state.records.find(element => element.id === recordId);
      if(rate) {
        const recordIndex = state.records.indexOf(rate);
        state.records[recordIndex].ratingCount = state.records[recordIndex].ratingCount + 1;
        //alert(`hejo ${state.records.indexOf(rate)}`) // finding each record in array
        //console.log(`Ta płyta ma indeks ${recordIndex}`);
        state.records[recordIndex].sumOfRating = parseInt(sumOfRating + rating);
        console.log(`Rating count: ${state.records[recordIndex].ratingCount}`);
        console.log(`${state.records[recordIndex]}`);
      }
    },
    [types.DEL_ALBUM]: (state, {recordId, name, rating}) => {
      const record = state.records.find(element => element.id === recordId);
      if(record) {
        state.records.splice(state.records.indexOf(record), 1);
      }
    },
    [types.SET_REVIEWS]: (state, review) => {
      /*const reviews = state.records.find(element => element.id === review.id);
      if(reviews) {
        const recordIndex = state.records.indexOf(reviews);
        state.records[recordIndex].comment = review.comment;
      }*/
      const reviews = state.records.find(element => element.id === review.id);
      console.log(reviews);
      const recordIndex = state.records.indexOf(reviews);
      state.records[recordIndex].comment = review.comment;
    }
  },
  actions: {
    [types.ADD_TO_OWNED]: ({commit}, album) => {
      commit(types.ADD_RECORD, album);
    },
    [types.USER_RATE]: ({commit}, userRate) => {
      commit(types.SET_RATING, userRate);
    },
    [types.USER_REVIEW]: ({commit}, review) => {
      axios.post('/api/cds', review)
        .then(resp => console.log(resp))
        .catch(error => console.log(error));
      commit(types.SET_REVIEWS, review);
    },
  }
};

export default userProfile;
