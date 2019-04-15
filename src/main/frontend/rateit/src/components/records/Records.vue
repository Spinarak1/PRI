<template>
    <div>
        <app-record v-for="record in records" :record="record"></app-record>
    </div>
</template>

<script>
import Record from './Record.vue'
export default {
    data() {
        return {
            records: [
                { id: 1, bandName: 'Slayer', albumName: 'Reign in Blood', year: 1986},
                { id: 2, bandName: 'Rzabka', albumName: 'Frankenrzab', year: 2013},
                { id: 3, bandName: 'Anthrax', albumName: 'Among the Living', year: 1986},
                { id: 4, bandName: 'Metallica', albumName: 'Master of Puppets', year: 1986},
                { id: 5, bandName: 'Metallica', albumName: 'Master of Puppets', year: 1986},
            ]
        }
    },
    components: {
        appRecord: Record
    }
}
</script>

