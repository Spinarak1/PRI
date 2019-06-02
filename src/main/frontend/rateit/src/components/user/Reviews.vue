<template>
  <div class="panel panel-default">
    <div class="panel-body">
      <div class="panel-body">
        <router-link to="/userProfile">Back</router-link>
        <hr>
        <h2 style="text-align: center">My Reviews</h2>
        <hr>
        <div class="col-sm-12 col-md-6" v-for="record in recordsShow">
          <div class="panel panel-default">
            <div class="panel-body">
              {{ record.name }} <br>
              <small>{{ parseInt(record.released)}}</small>
              <small>"{{record.comment}}"</small>
              <star-rating
                :increment=0.5
                :star-size="20"
                :rating=1
                :read-only=true>
              </star-rating>
              <textarea
                  id=""
                  cols="60"
                  rows="10"
                  placeholder="add a review"
                  v-model="review"
              ></textarea>
              <button
                class="btn btn-primary"
                style="float:right"
                @click="albumRevied(record.id, record.comment, record.name, record.rating, record.ratingCount, record.released)"
              >Save</button>
              <p v-for="review in getReview">{{ review }}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex'
  import { mapActions } from 'vuex'
  import StarRating from 'vue-star-rating'
  import * as types from '../../store/types'
  export default {

    data() {
      return {
        review: ''
      }
    },

    computed: {
      /*...mapGetters([
        "recordsShow",
        "getRating"
      ]),
      ...mapActions([
        "setRatings"
      ])*/
      ...mapGetters({
        recordsShow: types.OWNED_RECORDS,
        getRating: types.GET_RATING,
        getReview: types.GET_REVIEWS
      }),
      ...mapActions({

      })
    },
    methods: {
      albumRevied(id, comment, name, rating, ratingCount, released) {
        //console.log(`${name}, ${this.review}, ${parseInt(released)}, ${rating}`);
        const review = {
          id: id,
          comment: this.review,
          name: name,
          rating: rating,
          ratingCount: ratingCount,
          released: released,
        };
        console.log(review);
        this.$store.dispatch(types.USER_REVIEW, review)

      }
    },
    components: {
      StarRating
    }
  }
</script>
