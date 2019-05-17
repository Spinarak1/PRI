<template>
    <div>
        <app-record></app-record>
        <button @click="addTo">No dawaj</button>
        <button @click="addTo2">No dawaj2</button>
    </div>
</template>

<script>
import { AXIOS } from '../../main';
import Record from './Record.vue'
import { mapGetters } from 'vuex'
import { mapActions } from 'vuex'

export default {
    data() {
      return {
        data: { id: 1, nick: 'ss', email: 'sss@gmail.com', password: 'qwerty', score: 143, badges: 'ss', cd: [] }
      }
    },

    methods: {
      addTo2() {
        AXIOS.get('api/users')
          .then(resp => {
            console.log(resp)
          })
          .catch(error => console.log(error));
      },
      addTo() {
        AXIOS.post('api/users', this.data)
          .then(resp => {
            console.log(resp);
          })
          .catch(error => console.log(error))
      }
    },
    computed: {
      /*...mapGetters([
        'arrLen',
        'stringU'
        ])

      ...mapActions([
        'addTo'
      ])*/
      },

    components: {
        appRecord: Record
    }
}
</script>

