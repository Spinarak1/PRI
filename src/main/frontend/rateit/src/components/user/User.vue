<template>
  <div class="panel panel-default">
    <div class="panel-body">
      <div class="container" role="main">
        <div class="row">
          <div class="col-sm-8">
            <div class="row">
              <div class="col-sm-3">
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
              <div class="col-sm-6">
                <div class="panel panel-default">
                  <div class="panel-body">
                    <img v-bind:src="img" alt="">
                  </div>
                </div>
              </div>
                <!--<div class="col-sm-8">
                  <div class="panel panel-default">
                    <div class="panel-body">
                      <h1>{{userDetails[0].nick}}</h1>
                    </div>
                  </div>
                <div class="col-sm-12">
                  <div class="panel panel-default">
                    <div class="panel-body">
                      <p>{{userDetails[0].email}}</p>
                    </div>
                  </div>
                </div>
                  <div class="col-sm-12">
                    <div class="panel panel-default">
                      <div class="panel-body">
                        <p>{{parseInt(userDetails[0].registrationDate)}}</p>
                      </div>
                    </div>
                  </div>
                <div class="col-sm-12">
                  <div class="panel panel-default">
                    <div class="panel-body">
                      <p>Score: {{userDetails[0].score}}</p>
                    </div>
                  </div>
                </div> -->

              <div class="col-sm-3">
                <div class="panel panel-default">
                  <div class="panel-body">
                    <div class="row">{{ userDetails[0].nick }}</div>
                    <hr>
                    <div class="row">{{ userDetails[0].email }}</div>
                    <hr>
                    <div class="row">{{ parseFloat(userDetails[0].registrationDate)}}</div>
                    <hr>
                    <div class="row"><router-link to="">Settings</router-link></div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import { mapGetters } from "vuex";
  import * as types from "../../store/types";
  export default {
    data() {
      return {
        img: 'https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/220px-User_icon_2.svg.png',
      }
    },
    computed: {
      ...mapGetters({
        userDetails: types.USER_DETAILS
      })
    }
  }
</script>
<style scoped>
  .row {
    text-align: center;
  }
</style>
