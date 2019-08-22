<template>
  <div class="grey darken-2">
    <v-app dark>
      <v-content fluid>

        <router-view>

        </router-view>

      </v-content>
    </v-app>
  </div>
</template>

<script>
  import Navbar from "./components/shared/Navbar";
  import Footer from "./components/shared/Footer";
  export default {
    name: 'App',
    components: {
      Navbar,
      Footer
    },
    data: () => ({
      //
    }),
  };
</script>

<style >
</style>