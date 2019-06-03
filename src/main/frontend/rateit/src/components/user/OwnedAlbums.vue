<template>
  <div class="panel panel-default">
    <div class="panel-body">
      <div class="panel-body">
        <router-link to="/userProfile">Back</router-link>
        <hr>
        <h2 style="text-align: center">Owned Albums</h2>
        <!--<p>{{ getRating }}</p>-->
        <hr>
        <div class="col-sm-12 col-md-6" v-for="record in ownedRecords">
          <div class="panel panel-default">
            <div class="panel-body">
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
            <p>ID:{{record.id}} NAME: {{record.name}} RELEASED: {{record.released}} RATINGCOUNT:{{record.ratingCount}} SUMOFRATING:{{record.sumOfRating}}</p>
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
  export default {
    data() {
      return {
        rating: this.rating,
        review: ''
      }
    },
    created() {

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
        console.log(`User rate: ${rating}`);
      },
      albumRated(id, comment, name, released, ratingCount, sumOfRating){
        console.log(`Eloo ${id} ${comment} ${name}, ${released}, ${this.rating} ${ratingCount} ${sumOfRating}`);
        // sending to Vuex: recordId, name, released, rating

        const userRate = {
          recordId: id,
          comment: this.review,
          name: name,
          released: released,
          rating: this.rating,
          ratingCount: ratingCount,
          sumOfRating: sumOfRating
        };
        console.log(userRate);
        this.$store.dispatch(types.USER_RATE, userRate);
      },
    },
    components: {
      StarRating
    }
}
</script>
