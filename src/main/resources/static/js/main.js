import Vue from 'vue'
import '@babel/polyfill'
import App from 'pages/App.vue'
import 'api/resource'
import store from 'store/publications-store.js'

new Vue({
    el: '#app',
    store,
    render: a => a(App)
})
