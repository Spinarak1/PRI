<template>
  <div class="container">
    <div class="row justify-content-md-center">
      <div class="panel panel-default">
        <div class="panel-body">
          <app-record></app-record>
        </div>
      </div>
    </div>
  </div>
</template>



<script>
import Record from './Record.vue'

export default {

    components: {
        appRecord: Record
    }
}
</script>
