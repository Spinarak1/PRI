<template>
  <div class="panel panel-default">
    <div class="panel-body">
      <div class="panel-body">
        <router-link to="/user">Back</router-link>
        <hr>
        <h2>Owned Albums</h2>
      </div>
    </div>
  </div>
</template>

<script>
export default {
    props: ['record']
}
</script>
