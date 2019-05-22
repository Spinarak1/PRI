<template>
  <div>
      <div class="col-sm-6 col-md-3" v-for="ar in showRecords">
        <div class="panel panel-default">
          <div class="panel-body">
            {{ ar.name }} <br/>
            <small>{{ parseInt(ar.released)}}</small>

            <star-rating
                :star-size="15"
                :rating="ar.rating"
                @rating-selected ="setRating">

            </star-rating>
            <button class="btn btn-primary" style="float: right" @click="addAlbum(ar.name, ar.rating)">Add</button>
          </div>
        </div>
    </div>
   <!-- <p>Store : {{records[0].name}}</p>
    <p>Getters : {{showRecords}}</p> -->

  </div>
</template>

<script>
  import User from '../user/User.vue'
  import StarRating from 'vue-star-rating'
  import { mapGetters } from 'vuex'

export default {
    data() {
      return {
        arr: [],
        obj: {
          name: 'Paździoch',
          released: '2010-01-01',
          rating: 4
        }
      }
    },
    methods: {
      setRating: (rating) => {
        this.rating = rating;
        console.log(`Your rating is ${rating}`);
      },
      addAlbum(name, rating) {
        var album = {
          name: name,
          rating: rating
        };
        console.log(album);
        var a = 'aaaa';
        eventBus.$emit('addedAlbum', album);
      }
    },
    created() {
      this.$store.dispatch("fetchAlbums");
    },
    computed: {
      ...mapGetters([
        "showRecords"
      ])
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
