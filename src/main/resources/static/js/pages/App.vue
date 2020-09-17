<template>
    <v-app>
        <v-app-bar app>
            <v-app-bar-nav-icon></v-app-bar-nav-icon>
            <v-toolbar-title>Web Site</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-btn icon href="/logout">
                <v-icon>mdi-exit-to-app</v-icon>
            </v-btn>
        </v-app-bar>
        <v-main>
            <v-container>
                <publications-list :publications="publications"/>
            </v-container>
        </v-main>
    </v-app>
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