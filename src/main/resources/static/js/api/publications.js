import Vue from 'vue'

const publications = Vue.resource('/publication{/id}')

export default {
    add: publication => publications.save({}, publication),
    getAll: () => publications.get({}),
    getById: id => publications.get({id}),
    update: publication => publications.update({id: publication.id}, publication),
    remove: id => publications.remove({id}),
    page: page => Vue.http.get('/publication', {params: { page }})
}