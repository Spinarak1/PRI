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
import axios from 'axios'
import Record from './Record.vue'
import { mapGetters } from 'vuex'
import { mapActions } from 'vuex'

export default {

    components: {
        appRecord: Record
    }
}
</script>
