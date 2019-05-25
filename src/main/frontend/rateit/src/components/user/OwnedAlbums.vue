<template>
  <div class="panel panel-default">
    <div class="panel-body">
      <div class="panel-body">
        <router-link to="/user">Back</router-link>
        <hr>
        <h2>Owned Albums</h2>
        <div class="col-sm-6 col-md-3" v-for="record in recordsShow">
          <div class="panel panel-default">
            <div class="panel-body">
              {{ record.name }} <br>
              <small>{{ parseInt(record.released)}}</small>
              <star-rating
                :star-size="15"
                :rating="record.rating">
              </star-rating>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex'
  import StarRating from 'vue-star-rating'
  export default {

    created() {

    },
    computed: {
      ...mapGetters([
        "recordsShow"
      ])
    },
    components: {
      StarRating
    }
}
</script>
