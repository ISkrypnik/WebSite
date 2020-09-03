<template>
    <div style="position: relative; width: 300px;">
        <publication-form :publicationAttr="publication"/>
        <publication-row v-for="publication in publications"
                         :key="publication.id"
                         :publication="publication"
                         :editPublication="editPublication"
                         />
    </div>
</template>

<script>
    import {mapActions, mapGetters} from 'vuex'
    import PublicationRow from "components/publications/PublicationRow.vue"
    import PublicationForm from "components/publications/PublicationForm.vue"

    export default {
        // props: ['publications'],
        components: {
            PublicationRow,
            PublicationForm
        },
        data() {
            return {
                publication: null
            }
        },
        // created: mapActions(['initPublicationsAction']),
      created() {
          this.getInitialFetch()
      },
      computed: mapGetters(['publications']),



      // Moved to publication-store.initPublicationsAction
      // created: function () {
        //     this.$resource('/publication{/id}').get().then(response =>
        //         response.json().then(data =>
        //             data.forEach(publication => this.publications.push(publication))
        //         )
        //     )
        // },
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