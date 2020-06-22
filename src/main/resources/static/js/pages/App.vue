<template>
    <publications-list :publications="publications"/>
</template>

<script>
    import PublicationsList from "../components/publications/PublicationsList.vue";

    export default {
        components: {
            PublicationsList
        },
        data() {
            return {
                publications: []
            }
        }
    }
</script>

<style>

</style>