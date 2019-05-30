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
                    <div class="row"> <router-link to="/userProfile">My Account</router-link></div>
                    <hr>
                    <div class="row"><router-link to="/userProfile/owned">My Albums</router-link></div>
                    <hr>
                    <div class="row"><router-link to="/userProfile/reviews">My Reviews</router-link></div>
                    <hr>
                    <div class="row"><router-link to="/userProfile/wishlist">Wishlist</router-link></div>
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
