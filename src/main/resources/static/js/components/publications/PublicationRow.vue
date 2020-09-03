<template>
    <div>
        <i>({{ publication.id }})</i> Name: {{ publication.name }}. Text: {{ publication.text }}
        <span style="position: absolute; right: 0">
        <input type="button" value="Edit" @click="edit"/>
        <input type="button" value="X" @click="del"/>
        </span>
    </div>
</template>

<script>
  import {mapActions} from 'vuex'

    export default {
        props: ['publication', 'editPublication'],
        methods: {
          ...mapActions(['removePublicationAction']),
            edit() {
                this.editPublication(this.publication)
            },
            del() {
                this.removePublicationAction(this.publication)
            }
        }
    }
</script>

<style>

</style>





