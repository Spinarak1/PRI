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
    [types.SET_RATING]: (state, {recordId, comment, name, released, rating, ratingCount, sumOfRating }) => {

      //console.log(state.records);
      const rate = state.records.find(element => element.id === recordId);
      if(rate) {
        const recordIndex = state.records.indexOf(rate);
        state.records[recordIndex].ratingCount = state.records[recordIndex].ratingCount + 1;
        //alert(`hejo ${state.records.indexOf(rate)}`) // finding each record in array
        //console.log(`Ta płyta ma indeks ${recordIndex}`);
        state.records[recordIndex].sumOfRating = parseInt(sumOfRating + rating);
        state.records[recordIndex].comment = comment;
        state.records[recordIndex].rating = rating;
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
  },
  actions: {
    [types.ADD_TO_OWNED]: ({commit}, album) => {
      commit(types.ADD_RECORD, album);
    },
    [types.USER_RATE]: ({commit}, userRate) => {
      axios.post('/api/cds', userRate)
        .then(resp => console.log(resp))
        .catch(error => console.log(error));
      commit(types.SET_RATING, userRate);
    },
  }
};

export default userProfile;
