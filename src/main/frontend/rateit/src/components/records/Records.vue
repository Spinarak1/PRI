<template>
    <div>
        <app-record></app-record>
        <button @click="addTo">POST </button>
        <button @click="addTo2">GET </button>
    </div>
</template>

<script>
import { AXIOS } from '../../main';
import axios from 'axios'
import Record from './Record.vue'
import { mapGetters } from 'vuex'
import { mapActions } from 'vuex'

export default {
    data() {
      return {
        data: { id: 3, nick: 'GolyJelen', email: 'golyjelen@gmail.com', password: 'qwerty', score: 143, badges: 'ss', cd: [] }
      }
    },

    methods: {
      addTo2() {
        axios.get('api/users')
          .then(resp => {
            console.log(resp)
          })
          .catch(error => console.log(error));
      },
      addTo() {
        axios.post('api/users', this.data)
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

