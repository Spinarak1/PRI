<template>
  <div class="panel panel-default">
    <div class="panel-body">
      <div class="card text-center">
        <div class="card-header">
          <ul class="nav nav-pills card-header-pills">
            <li class="nav-item">
              <router-link to="/owned">Owned Albums</router-link>
            </li>
            <li class="nav-item">
              <router-link to="/reviews">Reviews</router-link>
            </li>
            <li class="nav-item">
              <router-link to="/wishlist">Wishlist</router-link>
            </li>
            <li class="nav-item">
              <router-link to="/rates">Rates</router-link>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

export default {
    data() {
        return {
            record: []
        }
    }
   
}
</script>

<style scoped>
  ul {
    list-style: none;
    margin: 0;
    padding: 0;
    height: 100%;
    align-items: center;
  }
</style>
