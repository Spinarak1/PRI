<template>
  <div class="panel panel-default">
    <div class="panel-body">
      <div class="panel-body">
        <router-link to="/userProfile">Back</router-link>
        <hr>
        <h2 style="text-align: center">Owned Albums</h2>
        <!--<p>{{ getRating }}</p>-->
        <hr>
        <div class="col-sm-12 col-md-12" v-for="record in ownedRecords">
          <div class="panel panel-default">
            <div class="panel-body sec">
              <div class="img">
                <img src="http://3.imimg.com/data3/EA/IK/GLADMIN-2746/video-cd-250x250.jpg" alt="">
                <small>{{record.photoURL}}</small>
              </div>
              <div class="recordData">
                {{ record.name }} <br>
                <small>{{ parseInt(record.released)}}</small> <br>
                <small>"{{record.comment}}"</small>
                <small>Average:({{(record.sumOfRating / record.ratingCount).toFixed(2)}})</small>
                <star-rating
                  :increment=0.5
                  :star-size="20"
                  :rating="record.rating"
                  @rating-selected="setRating" >
                </star-rating>
                <textarea
                  id=""
                  cols="60"
                  rows="6"
                  placeholder="add a review"
                  v-model="review"
                ></textarea>
              </div>

              <button
                class="btn btn-primary"
                style="float:right"
                @click="albumRated(
                  record.id,
                  record.comment,
                  record.name,
                  record.released,
                  record.ratingCount,
                  record.sumOfRating
                  )"
                >Save</button>
            </div>
            <p>ID:{{record.id}} NAME: {{record.name}} COMMENT: {{record.comment}} RELEASED: {{record.released}} RATINGCOUNT:{{record.ratingCount}} SUMOFRATING:{{record.sumOfRating}}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex'
  import { mapActions } from 'vuex'
  import * as types from '../../store/types'
  import StarRating from 'vue-star-rating'
  import axios from 'axios'
  export default {
    data() {
      return {
        rating: this.rating,
        review: '',
        url: ''
      }
    },
    created() {
      axios.get('')
    },
    computed: {
      /*...mapGetters([
        "recordsShow",
        "getRating"
      ]),
      ...mapActions({
        setRatings: types.USER_RATE,
      }),*/
      ...mapGetters({
        ownedRecords: types.OWNED_RECORDS,
        getRating: types.GET_RATING,
        getReview: types.GET_REVIEWS,
      })
    },
    methods: {
      setRating(rating) {
        this.rating = rating;
        //console.log(`User rate: ${rating}`);
      },
      albumRated(id, comment, name, released, ratingCount, sumOfRating){
        //console.log(`ID: ${id} | Comment: ${comment} | Name: ${name} | Released: ${released} | Rating: ${this.rating} | RatingCount: ${ratingCount} | SumOfRating: ${sumOfRating}`);
        // sending to Vuex: recordId, name, released, rating

        const userRate = {
          recordId: id,
          name: name,
          released: released,
          rating: this.rating,
          comment: this.review,
          ratingCount: ratingCount,
          sumOfRating: sumOfRating
        };
        //console.log('Heja ' + userRate.comment);
        console.log('PA na tO ' + userRate.recordId + userRate.name + userRate.released + userRate.comment + userRate.rating + userRate.ratingCount + userRate.sumOfRating);
        this.$store.dispatch(types.USER_RATE, userRate);
        this.review = '';
        console.log('---------');
        console.log(this.ownedRecords);
      },
    },
    components: {
      StarRating
    }
}
</script>

<style scoped>
  .sec:hover {
    cursor: pointer;
    background-color: #8d4288;

  }
  .img {
    width: 255px;
    height: 255px;
    border: 1px #8d4288 solid;
    float: left;
    margin: 20px;
  }
  .recordData{
    margin: 20px;
  }
</style>
