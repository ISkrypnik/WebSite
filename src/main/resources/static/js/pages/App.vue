<template>
    <publications-list :publications="publications"/>
</template>

<script>
    import PublicationsList from "../components/publications/PublicationsList.vue";
    import {mapActions} from "vuex";

    export default {
        components: {
            PublicationsList
        },
        // created() {
        //   // ...mapActions(['initPublicationsAction']),
        //   console.log('CREATED APP')
        //   mapActions(['initPublicationsAction']),
        //       this.initPublicationsAction
        // },
        data() {
            return {
                publications: []
            }
        }
    }
</script>

<style>

</style>