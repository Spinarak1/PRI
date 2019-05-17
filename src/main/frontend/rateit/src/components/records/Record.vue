<template>
    <div class="col-sm-6 com-md-4">
        <div v-for="record in records" class="panel panel-success">
            <div class="panel-heading">
                <h3 class="panel-title">{{record.name}}</h3>
            </div>
            <div class="panel-body">
              {{record.released}}
            </div>
        </div>
    </div>
</template>

<script>
import User from '../user/User.vue'

export default {
    computed: {
      records() {
        return this.$store.state.records;
      }
    },
    components: {
        appUser: User
    }
}
</script>
