<template>
    <div class="col-sm-6 com-md-4">
        <div class="panel panel-success">
            <div class="panel-heading">
                <h3 class="panel-title"> {{ record.name }}</h3>
            </div>
            <div class="panel-body">
                <div class="pull-left">
                    {{ record.name }} <br>
                    <small>(Year: {{ parseInt(record.released) }})</small>
                </div>
                <div class="pull-right">
                    <button
                    class="btn btn-success"
                    @click="addRecord"
                    >Add</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import User from '../user/User.vue'

export default {

    props: ['record'],
    data() {
      return {
        msg: 'ehj'
      }
    },
    methods: {
        addRecord() {
            const order = {
                recordId: this.record.id,
                recordName: this.record.bandName,
                recordAlbum: this.record.albumName
            };
            //console.log(order);
        }
    },

    components: {
        appUser: User
    }
}
</script>
