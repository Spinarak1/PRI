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
              <star-rating
                :increment=0.5
                :star-size="20"
                :rating="getRating.rating"
                :read-only=true>
              </star-rating>
              <textarea
                  id=""
                  cols="60"
                  rows="10"
              ></textarea>
              <button
                class="btn btn-primary"
                style="float:right"
                @click="albumReviewd"
              >Save</button>
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
  export default {

    data() {
      return {
        review: 'lorem ipsum'
      }
    },

    computed: {
      ...mapGetters([
        "recordsShow",
        "getRating"
      ]),
      ...mapActions([
        "setRatings"
      ])
    },
    methods: {
      albumReviewd() {
        console.log(this.review)
      }
    },
    components: {
      StarRating
    }
  }
</script>
