<template>
  <div id="dashboard">
    <h1>That's the dashboard!</h1>
    <p>You should only get here if you're authenticated!</p>
    <p>Hello {{ user }}, what are you listening to?</p>
  </div>
</template>

<script>
  import { AXIOS} from "../../main";

  export default {
    data() {
      return {
          user: ''
      }
    },
    created() {
      AXIOS.get('/api/users')
        .then(resp => {
          console.log(resp.data[1].nick);
          this.user = resp.data[1].nick;

        })
        .catch(e => console.log(e));
    }
  }
</script>

<style scoped>
  h1, p {
    text-align: center;
  }

  p {
    color: red;
  }
</style>
