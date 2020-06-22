<template>
    <div style="position: relative; width: 300px;">
        <publication-form :publications="publications" :publicationAttr="publication"/>
        <publication-row v-for="publication in publications"
                         :key="publication.id"
                         :publication="publication"
                         :editPublication="editPublication"
                         :deletePublication="deletePublication"
                         :publications="publications"/>
    </div>
</template>

<script>
    import PublicationRow from "components/publications/PublicationRow.vue";
    import PublicationForm from "components/publications/PublicationForm.vue";

    export default {
        props: ['publications'],
        components: {
            PublicationRow,
            PublicationForm
        },
        data() {
            return {
                publication: null
            }
        },
        created: function () {
            this.$resource('/publication{/id}').get().then(response =>
                response.json().then(data =>
                    data.forEach(publication => this.publications.push(publication))
                )
            )
        },
        methods: {
            editPublication(publication) {
                this.publication = publication
            },
            deletePublication(publication) {
                this.$resource('/publication{/id}').remove({id: publication.id}).then(result => {
                    if (result.ok) {
                        this.publications.splice(this.publications.indexOf(publication), 1)
                    }
                })
            }
        }
    }
</script>

<style>

</style>