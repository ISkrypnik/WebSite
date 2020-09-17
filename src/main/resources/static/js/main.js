import Vue from 'vue'
import '@babel/polyfill'
import App from 'pages/App.vue'
import 'api/resource'
import store from 'store/publications-store.js'
import vuetify from "./plugins/vuetify"

new Vue({
    el: '#app',
    store,
    vuetify,
    render: a => a(App)
})
