<template>
  <div>
    <div class="active-purple-4 mb-4">
      <input
        class="form-control"
        type="text"
        placeholder="Search"
        aria-label="Search"
        v-model="filterText">
      <button class="btn btn-primary" @click="toggler = !toggler">Advanced</button>
      <appSearch
        v-if="toggler"
        @advancedSearch="search = $event"></appSearch>
    </div>
    <br>
        <div class="col-sm-12 col-md-12" v-for="record in filterData">
          <div class="panel panel-default">
            <p>{{search}}</p>
            <div class="panel-body">
              <div class="img">
                <img
                  style="max-height: 250px; max-width: 250px"
                  v-bind:src="url[0]"
                  alt="">
              </div>
              <p>{{record.id-2}}</p>
              <div class="recordData">
                {{ record.name }} <br>
                <small>{{ parseInt(record.released)}}</small>
                <star-rating
                  :increment=0.5
                  :star-size="15"
                  :rating="record.sumOfRating / record.ratingCount"
                  :read-only=true>
                </star-rating>
                <small>"{{ record.comment }}"</small>
              </div>
              <button
                class="btn btn-primary"
                style="float: right"
                @click="addAlbum(record.id, record.comment, record.name, record.ratingCount, record.sumOfRating, record.released)"
              >Add</button>
            </div>
          </div>
        </div>
    <p>ee{{showRecords}}</p>
  </div>
</template>

<script>
  import User from '../user/User.vue'
  import StarRating from 'vue-star-rating'
  import { mapGetters } from 'vuex'
  import { mapState } from 'vuex'
  import { mapActions } from 'vuex'
  import * as types from '../../store/types'
  import AdvancedSearch from './AdvancedSearch'
  import axios from 'axios'

export default {
    data() {
      return {
        filterText: '',
        recordName: [],
        toggler: false,
        search: {},
        url: [],
        urlIndex: 1
      }
    },
    created() {
      console.log(this.showRecords.photoURL);

    },
    methods: {
      addAlbum(recordId, comment, name, ratingCount, sumOfRating, released) {
        const album = {
          recordId: recordId,
          comment: comment,
          name: name,
          ratingCount: ratingCount,
          sumOfRating: sumOfRating,
          released: released
        };
        this.$store.dispatch(types.ADD_TO_OWNED, album);
        console.log(album);
        //console.log(this.recordsShow);
        //this.$store.dispatch("userProfile/addRecords", album);
      },
      advancedSearch(){

      },
    },
    mounted() {
      this.$store.dispatch(types.FETCH_ALBUMS);
      this.$store.dispatch(types.FETCH_IMAGE);

      //this.url.push(showRecords)
      axios.get('/api/cds')
        .then(resp => {
          const data = resp.data;
          console.log(data);
          data.forEach(curr => {
            console.log(curr.photoURL);
            this.url.push(curr.photoURL);
          })
        })
        .catch(e => console.log(e));
      console.log(this.photoURL);
    },
   computed: {
      /*...mapActions('userProfile',[
        "addRecords"
      ]),*/
     ...mapActions({
       fetchAlbums: types.FETCH_ALBUMS,
       addRecords: types.ADD_TO_OWNED,
       fetchImage: types.FETCH_IMAGE,
     }),
      ...mapGetters({
        showRecords: types.SHOW_RECORDS,
        showImages: types.SHOW_IMAGES,

      }),
      ...mapState([
        "records"
      ]),
     filterData() {
       if(this.toggler === false) {
         return this.showRecords.filter((element) => {
           return (element.name.match(this.filterText));
         });
       } else {
         return this.showRecords.filter(element => {
           console.log("element.released" + parseInt(element.released));
           console.log(this.search.yearFrom);
           console.log(this.search.yearTo);
           if((parseInt(element.released) === this.search.yearFrom) && (parseInt(element.released) <= this.search.yearTo))
           {
             console.log('elo')
           }
           //return (element.released.match(this.search.yearFrom));
         })
       }

     }
    },

    components: {
      appUser: User,
      StarRating,
      appSearch: AdvancedSearch
    }
}
</script>

<style scoped>
  .panel-body:hover {
    cursor: pointer;
    background-color: #8d4288;
    color: white;
  }
  .img {
    width: 250px;
    height: 250px;
    border: 1px #8d4288 solid;
    float: left;
    margin: 20px;
  }
  .recordData{
    margin: 20px;
  }
</style>
