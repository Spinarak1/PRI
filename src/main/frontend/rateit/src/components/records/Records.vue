<template>
    <div>
        <app-record v-for="record in records" :record="record" ></app-record>
    </div>
</template>

<script>

import Record from './Record.vue'
import axios from 'axios'

export default {
    data() {
        return {
          res: '',
          records: []
        }
    },
    created() {
      axios.get('http://localhost:8080/cds')
        .then(resp => {
          const data = resp.data;
          //console.log(data[0].name);
          data.forEach(cur => {
            //console.log(cur);
            this.records.push(cur)
          })
        })
        .catch(error => console.log(error))

    },
    components: {
        appRecord: Record
    }
}
</script>

