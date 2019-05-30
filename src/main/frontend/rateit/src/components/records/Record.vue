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
                :increment=0.5
                :star-size="15"
                :rating="record.rating"
                :read-only=true>
              </star-rating>

              <button
                class="btn btn-primary"
                style="float: right"
                @click="addAlbum(record.id, record.name, record.released, record.rating)"
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
  import { mapActions } from 'vuex'
  import * as types from '../../store/types'

export default {
    data() {
      return {
        filterText: '',
        recordName: []
      }
    },
    methods: {
      addAlbum(recordId, name, released, rating) {
        const album = {
          recordId: recordId,
          name: name,
          released: released,
          rating: rating
        };
        this.$store.dispatch(types.ADD_TO_OWNED, album);
        console.log(album);
        //console.log(this.recordsShow);
        //this.$store.dispatch("userProfile/addRecords", album);
      },
    },
    created() {
      this.$store.dispatch(types.FETCH_ALBUMS);

    },
   computed: {
      /*...mapActions('userProfile',[
        "addRecords"
      ]),*/
     ...mapActions({
       fetchAlbums: types.FETCH_ALBUMS,
       addRecords: types.ADD_TO_OWNED
     }),
      ...mapGetters({
        showRecords: types.SHOW_RECORDS
      }),
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
