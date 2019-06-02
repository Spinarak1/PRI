<template>
  <div class="panel panel-default">
    <div class="panel-body">
      <div class="panel-body">
        <router-link to="/userProfile">Back</router-link>
        <hr>
        <h2 style="text-align: center">Owned Albums</h2>
        <!--<p>{{ getRating }}</p>-->
        <hr>
        <div class="col-sm-6 col-md-3" v-for="record in ownedRecords">
          <div class="panel panel-default">
            <div class="panel-body">
              {{ record.name }} <br>
              <small>{{ parseInt(record.released)}}</small>
              <star-rating
                :increment=0.5
                :star-size="20"
                :rating="record.rating"
                @rating-selected="setRating" >
                <!-- :rating="getRating.rating" -->

              </star-rating>
              <button
                class="btn btn-primary"
                style="float:right"
                @click="albumRated(record.id, record.name, record.released, record.ratingCount, record.sumOfRating)"
                >Save</button>
            </div>
            <p>{{record.id}} {{record.name}} {{record.released}} {{record.ratingCount}} {{record.sumOfRating}}</p>
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
        flag: true
      }
    },
    created() {

    },
    computed: {
      /*...mapGetters([
        "recordsShow",
        "getRating"
      ]),*/
      ...mapActions({
        setRatings: types.USER_RATE,
      }),
      ...mapGetters({
        ownedRecords: types.OWNED_RECORDS,
      })
    },
    methods: {
      setRating(rating) {
        this.rating = rating;
        console.log(`User rate: ${rating}`);
      },
      albumRated(id, name, released, ratingCount, sumOfRating){
        console.log(`Eloo ${id} ${name}, ${released}, ${this.rating}`)
        // sending to Vuex: recordId, name, released, rating

        const userRate = {
          recordId: id,
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
