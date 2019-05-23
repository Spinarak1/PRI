<template>
  <div>
    <div class="active-purple-4 mb-4">
      <input
        class="form-control"
        type="text"
        placeholder="Search"
        aria-label="Search"
        v-model="search">
    </div><br>
    <ul>
      <li v-for="ta in tab"> {{ ta }} </li>
    </ul>
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
        <p>{{search}}</p>
    </div>
   <!-- <p>Store : {{records[0].name}}</p>
    <p>Getters : {{showRecords}}</p> -->
    <p>{{tab}}</p>
  </div>
</template>

<script>
  import User from '../user/User.vue'
  import StarRating from 'vue-star-rating'
  import { mapGetters } from 'vuex'
  import { mapState } from 'vuex'

export default {
    data() {
      return {
        arr: [],
        search: '',
        tab: []
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
      this.tab = this.$store.state.records;
      console.log(this.$store.state.records);
    },
   computed: {
      ...mapGetters([
        "showRecords"
      ]),
      ...mapState([
        "records"
      ]),

     filteredAlbums() {
        return this.tab.filter(() => {
          return element.match(this.search)
        });
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
