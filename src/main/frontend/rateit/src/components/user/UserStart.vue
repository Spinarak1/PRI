<template>
  <div class="panel panel-default">
    <div class="panel-body">
    <div class="container" role="main">
      <div class="row">
        <div class="col-sm-8">
          <div class="row">
            <div class="col-sm-4">
              <div class="panel panel-default">
                <div class="panel-body">
                    <!--<li class="nav-item">
                      <router-link to="/user/owned">Owned Albums</router-link>
                    </li>
                    <li class="nav-item">
                      <router-link to="/user/reviews">Reviews</router-link>
                    </li>
                    <li class="nav-item">
                      <router-link to="/user/wishlist">Wishlist</router-link>
                    </li>
                    <li class="nav-item">
                      <router-link to="/user/rates">Rates</router-link>
                    </li>
                    <router-link to="/user" tag="li">My Account</router-link>
                    <router-link to="/user/owned" tag="li">My Albums</router-link>
                    <router-link to="user/reviews" tag="li">My Reviews</router-link>
                    <router-link to="Wishlist" tag="li">Wishlist</router-link>-->

                  <div class="row"> <router-link to="/user">My Account</router-link></div>
                  <hr>
                  <div class="row"><router-link to="/user">My Albums</router-link></div>
                  <hr>
                  <div class="row"><router-link to="/user">My Reviews</router-link></div>
                  <hr>
                  <div class="row"><router-link to="/user">Wishlist</router-link></div>
                </div>
              </div>
            </div>
              <div class="col-sm-4">1</div>
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
</template>

<style scoped>
  .row {
    text-align: center;
  }
</style>
