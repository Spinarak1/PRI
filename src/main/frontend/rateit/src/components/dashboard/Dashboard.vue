<template>
  <div id="dashboard">
    <h1>That's the dashboard!</h1>
    <p>You should only get here if you're authenticated!</p>
    <p>Hello {{ user }}, what are you listening to?</p>
  </div>
</template>

<script>

  import axios from 'axios'
  export default {
    data() {
      return {
          user: ''
      }
    },
    created() {
      axios.get(`/api/users`)
        .then(resp => {
          const data = resp.data;
          console.log(resp);
          const users = [];
          for(let key in data) {
            const user = data[key];
            users.push(user);

            //sconsole.log(user);
          }
          this.user = users[1].nick;

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
