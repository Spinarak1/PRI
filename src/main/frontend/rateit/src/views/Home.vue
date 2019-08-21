<template>
  <div class="home">
    <v-img
            src="http://getwallpapers.com/wallpaper/full/2/8/a/1292156-rock-concert-wallpaper-2880x1800-for-phone.jpg"
            height="100vh">
      <Navbar></Navbar>

      <v-container my-5>
        <v-layout row wrap justify-space-around align-center>
          <v-flex xs12 md7>
            <h1 style="text-align: center" class="display-4 white--text font-weight-bold mb-4">Rate you favourite music</h1>
          </v-flex>

          <v-flex xs12 md7 >
            <h2 style="text-align: center" class="white--text">And share it with your friends!</h2>
          </v-flex>
        </v-layout>

      </v-container>
    </v-img>
  </div>
</template>

<script>
import Navbar from "../components/shared/Navbar";
export default {
  components: {
    Navbar
  },
};
</script>

<style scoped>


</style>