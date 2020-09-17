<template>
    <v-card class="my-2">
        <v-list-item>
            <v-list-item-avatar color="grey"></v-list-item-avatar>
            <v-list-item-content>
                <v-list-item-title class="headline">{{ publication.name }}</v-list-item-title>
                <v-list-item-subtitle>by Author</v-list-item-subtitle>
            </v-list-item-content>
        </v-list-item>
        <v-img
            src="https://cdn.vuetifyjs.com/images/cards/mountain.jpg"
            height="194"
        />

        <v-card-text>
            {{ publication.text }}
        </v-card-text>

        <v-card-actions>
            <v-btn @click="edit">
                Edit
            </v-btn>
            <v-btn @click="del">
                <v-icon>mdi-delete-outline</v-icon>
            </v-btn>
        </v-card-actions>
    </v-card>
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





