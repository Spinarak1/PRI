<template>
  <div class="panel panel-default">
    <div class="panel-body">
      <div class="panel-body">
        <router-link to="/user">Back</router-link>
        <hr>
        <h2>Owned Albums</h2>
        <h1>{{record}}</h1>
        <button class="btn btn-primary" @click="poka">poka</button>
      </div>
    </div>
  </div>
</template>

<script>
  import { eventBus } from "../../main";

  export default {
    data() {
      return {
        record: {}
      }
    },
    created() {
      eventBus.$on('addedAlbum', (album) => {
        this.record = album;
        console.log(album.name)
      });
    },
    methods: {
      poka() {
        console.log(this.record);
      }
    }
}
</script>
