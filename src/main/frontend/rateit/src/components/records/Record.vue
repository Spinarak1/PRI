<template>
  <div>
      <div class="col-sm-6 col-md-3" v-for="ar in arr">
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
  </div>
</template>

<script>
  import User from '../user/User.vue'
  import axios from 'axios'
  import StarRating from 'vue-star-rating'

export default {
    data() {
      return {
        arr: [],
      }
    },
    methods: {
      setRating: (rating) => {
        this.rating = rating;
        console.log(`Your rating is ${rating},`)
      },
      addAlbum(name, rating) {
        var album = {
          name: name,
          rating: rating
        };
        console.log(album)
      }
    },
    created() {
      axios.get(`/api/cds`)
        .then(resp => {
          const data = resp.data;
          //console.log(data);
          data.forEach(cur => {
            console.log(cur);
            this.arr.push(cur);
          })
        })
        .catch(e => console.log(e));
      setTimeout(() => {
        console.log(`Tablitza: ${this.arr[0].name}`)
      }, 1500)

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
