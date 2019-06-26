<template>
  <div class="panel panel-default">
    <h1>Welcome to the Admin Dashboard!</h1>
    <hr>
    <div class="panel-body">
      <ul class="nav nav-pills card-header-pills">
        <li class="nav-item">
          <router-link to="/admin/addalbum">Add Album</router-link>
        </li>
        <!--<li class="nav-item">
          <router-link to="/reviews">Edit Album</router-link>
        </li>-->
        <li class="nav-item">
          <router-link to="/admin/adduser">Add User</router-link>
        </li>
        <!--<li class="nav-item">
          <router-link to="/rates">Edit User</router-link>
        </li>-->
        <li class="nav-item">
          <router-link to="/admin/listofusers">Show Users</router-link>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>

</script>

<style scoped>
  h1, p {
    text-align: center;
  }

  p {
    color: red;
  }
</style>
