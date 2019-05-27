<template>
  <div class="panel panel-default">
    <div class="panel-body">
      <div class="panel-body">
        <router-link to="/user">Back</router-link>
        <hr>
        <h2 style="text-align: center">Owned Albums</h2>
        <!--<p>{{ getRating }}</p>-->
        <hr>
        <div class="col-sm-6 col-md-3" v-for="record in recordsShow">
          <div class="panel panel-default">
            <div class="panel-body">
              {{ record.name }} <br>
              <small>{{ parseInt(record.released)}}</small>
              <star-rating
                :increment=0.5
                :star-size="20"
                :rating="getRating.rating"
                @rating-selected="setRating">

              </star-rating>
              <button
                class="btn btn-primary"
                style="float:right"
                @click="albumRated(record.id, record.name, record.released)"
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

    created() {

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
      setRating(rating) {
        this.rating = rating;
        console.log(`User rate: ${rating}`);
      },
      albumRated(id, name, released){
        //console.log(id,name,released, this.rating)
        // sending to Vuex: recordId, name, released, rating

        const userRate = {
          recordId: id,
          name: name,
          released: released,
          rating: this.rating
        };
        console.log(userRate);
        this.$store.dispatch("setRatings", userRate);
      },
    },
    components: {
      StarRating
    }
}
</script>
