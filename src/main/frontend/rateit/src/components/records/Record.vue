<template>
  <div>
      <div class="col-sm-6 col-md-3" v-for="ar in arr">
        <div class="panel panel-default">
          <div class="panel-body">
            {{ ar.name }} <br/>
            <small>{{ parseInt(ar.released)}}</small>
            <star-rating v-model="rating"></star-rating>
          </div>
        </div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  import User from '../user/User.vue'
  import StarRating from 'vue-star-rating'

export default {
    data() {
      return {
        arr: [],
        rating: {

        }
      }
    },
    created() {
      axios.get('/api/cds')
        .then(resp => {
          const data = resp.data;
          data.forEach(current => {
            this.arr.push(current);
            console.log(current)
          })
          //console.log(data[0].name);

          //console.log(this.arr);
          //this.arr.push(data);
        })
        .catch(error => console.log(error));
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
