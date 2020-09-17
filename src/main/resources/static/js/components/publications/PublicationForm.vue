<template>
    <v-layout column>
        <v-text-field label="Publication name" placeholder="Write publication name" v-model="name" solo/>
        <v-text-field label="Publication text" placeholder="Write publication text" v-model="text" solo/>
        <v-btn @click="save">
            Save
        </v-btn>
    </v-layout>
</template>

<script>
import { mapActions } from 'vuex'

export default {
    props: ['publicationAttr'],
    data() {
        return {
            id: '',
            name: '',
            text: ''
        }
    },
    watch: {
        publicationAttr: function (newVal, oldVal) {
            this.id = newVal.id
            this.name = newVal.name
            this.text = newVal.text
        }
    },
    methods: {
        ...mapActions(['addPublicationAction', 'updatePublicationAction']),
        save() {
            let publication = {id: this.id, name: this.name, text: this.text};

            if (this.id) {
                this.updatePublicationAction(publication)
                this.id = ''
                this.name = ''
                this.text = ''
            } else {
                this.addPublicationAction(publication)
                this.name = ''
                this.text = ''
            }
        }
    }
}
</script>

<style>

</style>