import * as types from '../types';
import axios from '../../axios-auth';

const userProfile = {
  state: {
    activeUser: [],
    records: [],
  },

  // GETTERS

  getters: {
    [types.OWNED_RECORDS]: state => {
      return state.records;
    },
    [types.GET_RATING]:(state, {recordId}) => {
      /*return state.records.find(element => {
        if(element.id === recordId) {
          const avg = state.records[element.id].sumOfRating / state.records[element.id].ratingCount;
        }
      })*/
      return {
        avg: state.records.find(element => {
          if (element.id === recordId) {
            const avg = (state.records[element.id].sumOfRating / state.records[element.id].ratingCount);
          }
        })
      }
      // side note - don't know if it's gonna return boolean or a certain value
    },

    [types.GET_REVIEWS]: (state, review) => {
      return state.records.find(element => element.id === review.id); // TODO
    },

    [types.USER_DETAILS]: state => {
      return state.activeUser;
    }
  },

  // MUTATIONS

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
          released: released,
          rating: null
        })
      } else {
        alert('This record is already on your list!');
      }
    },  // 04.06.2019 godzina 19:00
    [types.SET_RATING]: (state, userRate) => {

      //console.log(state.records);
      const rate = state.records.find(element => element.id === userRate.recordId);
      if(rate) {
        const recordIndex = state.records.indexOf(rate);
        state.records[recordIndex].ratingCount = state.records[recordIndex].ratingCount + 1;
        //alert(`hejo ${state.records.indexOf(rate)}`) // finding each record in array
        //console.log(`Ta płyta ma indeks ${recordIndex}`);
        state.records[recordIndex].sumOfRating = parseInt(userRate.sumOfRating + userRate.rating);
        state.records[recordIndex].comment = userRate.comment;
        state.records[recordIndex].rating = userRate.rating;
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
    [types.USER_DATA]: (state, response) => {

      if(state.activeUser.length > 0) {
        state.activeUser.length = 0;
        state.activeUser.push(response);
      } else {
        state.activeUser.push(response);
      }
    },
    [types.LOG_OUT]: state => {
      state.activeUser.length = 0;
    }
  },

  // ACTIONS

  actions: {
    [types.ADD_TO_OWNED]: ({commit}, album) => {
      commit(types.ADD_RECORD, album);
    },
    /*
    rating: this.rating,
          comment: this.review,
          ratingCount: ratingCount,
          sumOfRating: sumOfRating
     */
    [types.USER_RATE]: ({commit}, userRate) => {
      axios.put(`api/cds/${userRate.recordId}`, userRate)
        .then(resp => console.log(resp))
        .catch(error => console.log(error));
      commit(types.SET_RATING, userRate);
    },
    /*
          recordId: id,
          comment: this.review,
          name: name,
          released: released,
          rating: this.rating,
          ratingCount: ratingCount,
          sumOfRating: sumOfRating
     */
    //[types.UPDATE_RECORDS]
    [types.SIGN_IN]: ({commit}, formData) => {
      axios.post('/api/signin?nick='+formData.nick+'&password='+formData.password+'', formData)
        .then(resp => {
          const response = resp.data;
          console.log(response);
          if(!(Object.keys(response).length === 0)) {
            console.log('niepusty obiekt');
            console.log(response);
            console.log(response.roles);
            response.active = true;
            if(formData.roles === 'admin') {
              response.roles = 'admin';
            } else {
              response.roles = 'user';
            }
            commit(types.USER_DATA, response);
            alert(`zostales zalogowany AUUUU`);
          } else alert('złe passy')
        })
        .catch(e => console.log(e));
      console.log(formData);
    },
    [types.SIGN_OUT]: ({commit}) => {
      commit(types.LOG_OUT);
    }
  },
};

export default userProfile;
