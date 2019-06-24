<template>
  <div class="dashboard">
    <div class="panel panel-default" v-if="userDetails === 1">
      <div class="panel-body">

        <div class="row">
          <div class="panel-body">
            <div class="panel panel-default">
              <div class="panel-body">
                <div class="col-sm-6 col-md-4">
                  <img src="https://centrik.in/wp-content/uploads/2017/02/user-image-.png" alt="">
                </div>
                <div class="col-sm-6 col-md-8">
                  <h2>{{userDetails[0].nick}}</h2>
                  <p><strong>email: </strong>{{userDetails[0].email}}</p>
                  <p><strong>albums rated: </strong>{{}}</p>
                  <p><strong>score: </strong>score</p>
                  <p><strong>bagdes: </strong>badges</p>
                </div>
              </div>
              </div>
            </div>
        </div>
        <hr>
        <div class="row">
          <div class="panel-body">
            <h2>Activity</h2>
            <hr>
            <p>{{userDetails}}</p>
          </div>
        </div>

      </div>
    </div>

    <div v-else>
      <p>You are not allowed to see this page. </p> <p> Please, log in.</p>
    </div>
  </div>






  <!--<div id="dashboard">
    <div class="panel panel-default">
      <div class="panel-body">
        <div class="container" role="main">
          <div class="row">
            <div class="col-sm-8">
              <div class="row">
                <div class="col-sm-4">
                  <div class="panel panel-default">
                    <div class="panel-body">
                      <div class="row"><router-link to="/userProfile">My Account</router-link></div>
                      <hr>
                      <div class="row"><router-link to="/userProfile/owned">My Albums</router-link></div>
                      <hr>
                      <div class="row"><router-link to="/userProfile/reviews">My Reviews</router-link></div>
                      <hr>
                      <div class="row"><router-link to="/userProfile/wishlist">Wishlist</router-link></div>
                    </div>
                  </div>
                </div>
                <div class="col-sm-8"><h1>That's the dashboard!</h1>
                  <p>Hello {{ userDetails[0].nick }}, what are you listening to?</p>
                </div>
                <div class="col-sm-4">2</div>
                <div class="col-sm-4">3</div>
                <div class="col-sm-4">4</div>
                <div class="col-sm-4">5</div>
                <div class="col-sm-4">6</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>-->
</template>

<script>

  import axios from 'axios'
  import { mapGetters } from 'vuex';
  import * as types from '../../store/types';

  export default {
    data() {
      return {
        user: ''
      }
    },
    computed: {
      ...mapGetters({
        userDetails: types.USER_DETAILS
      })
    },
    created() {
      /*axios.get(`/api/users`)
        .then(resp => {
          const data = resp.data;
          console.log(resp);
          const users = [];
          for(let key in data) {
            const userProfile = data[key];
            users.push(user);

            //sconsole.log(userProfile);
          }
          this.user = users[1].nick;

        })
        .catch(e => console.log(e)); */
    }
  }
</script>

<style scoped>
  p {
    color: red;
    text-align: center;
    margin: 45px;
    font-size: 25px;
  }
</style>
