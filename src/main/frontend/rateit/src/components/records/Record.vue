<template>
  <div>
    <div class="active-purple-4 mb-4">
      <input
        class="form-control"
        type="text"
        placeholder="Search"
        aria-label="Search"
        v-model="filterText">
    </div>
    <br>
        <div class="col-sm-6 col-md-3" v-for="record in filterData">
          <div class="panel panel-default">
            <div class="panel-body">
              {{ record.name }} <br>
              <small>{{ parseInt(record.released)}}</small>
              <star-rating
                :star-size="15"
                :rating="record.rating"
                @rating-selected ="setRating">
              </star-rating>

              <button
                class="btn btn-primary"
                style="float: right"
                @click="addAlbum(record.name, record.rating)"
              >Add</button>
            </div>
          </div>
        </div>


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
        filterText: '',
        recordName: []
      }
    },
    methods: {
      setRating: (rating) => {
        this.rating = rating;
        console.log(`Your rating is ${rating}`);
        console.log(`Pa jaka masz tablitze ${this.albums}`)
      },
      addAlbum(name, rating) {
        var album = {
          name: name,
          rating: rating
        };
        console.log(album);
      },
    },
    created() {
      this.$store.dispatch("fetchAlbums");

    },
   computed: {
      ...mapGetters([
        "showRecords"
      ]),
      ...mapState([
        "records"
      ]),
     filterData() {
       return this.showRecords.filter((element) => {
          return (element.name.match(this.filterText));
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
