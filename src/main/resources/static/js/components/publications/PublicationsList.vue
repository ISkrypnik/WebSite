<template>
    <v-layout align-content-space-around justify-start column>
        <publication-form :publicationAttr="publication"/>
        <publication-row v-for="publication in publications"
                         :key="publication.id"
                         :publication="publication"
                         :editPublication="editPublication"
        />
    </v-layout>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
import PublicationRow from "components/publications/PublicationRow.vue"
import PublicationForm from "components/publications/PublicationForm.vue"

export default {
    components: {
        PublicationRow,
        PublicationForm
    },
    data() {
        return {
            publication: null
        }
    },
    created() {
        this.getInitialFetch()
    },
    computed: mapGetters(['publications']),
    methods: {
        ...mapActions(['initPublicationsAction']),
        getInitialFetch() {
            this.initPublicationsAction()
        },
        editPublication(publication) {
            this.publication = publication
        }
    }
}
</script>

<style>

</style>