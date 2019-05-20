<template>
  <div>
      <div class="col-sm-6 col-md-3" v-for="ar in arr">
        <div class="panel panel-default">
          <div class="panel-body">
            {{ ar.name }} <br/>
            <small>{{ parseInt(ar.released)}}</small>

            <star-rating
                :star-size="15"
                :rating="1.5"
                @rating-selected ="setRating">

            </star-rating>
          </div>
        </div>
    </div>
  </div>
</template>

<script>
  import User from '../user/User.vue'
  import StarRating from 'vue-star-rating'

export default {
    data() {
      return {
        arr: [],
        size: 15,
        rating: 0
      }
    },
    methods: {
      setRating: (rating) => {
        this.rating = rating;
        console.log(`Your rating is ${rating},`)
      }
    },
    created() {

    },
    computed: {
      records() {
        return this.$store.state.records;
      }
    },
    components: {
        appUser: User,
        StarRating
    }
}
</script>

<style scoped>
  .panel-body:hover {
    cursor: pointer;
    background-color: #8d4288;
    color: white;
  }
</style>
