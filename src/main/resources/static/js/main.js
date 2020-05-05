
let publicationApi = Vue.resource('/publication{/id}');

Vue.component('publication-form', {
    props: ['publications'],
    data: function() {
        return {
            name: '',
            text: ''
        }
    },
    template:
        '<div>' +
            '<input type="text" placeholder="Write publication name" v-model="name"/>' +
            '<input type="text" placeholder="Write publication text" v-model="text"/>' +
            '<input type="button" value="Save" @click="save"/>' +
        '</div>',
    methods: {
        save: function () {
            let publication = {name: this.name, text: this.text};
            publicationApi.save({}, publication).then(result =>
                result.json().then(data => {
                    this.publications.push(data);
                    this.name = '';
                    this.text = ''
                })
            )
        }
    }
});

Vue.component('publication-row', {
    props: ['publication'],
    template:
        '<div>' +
            '<i>({{ publication.id }})</i> Name: {{ publication.name }}. Text: {{ publication.text }}' +
        '</div>'
});

Vue.component('publications-list', {
    props: ['publications'],
    template:
        '<div>' +
            '<publication-form :publications="publications"/>' +
            '<publication-row v-for="publication in publications" :key="publication.id" :publication="publication" />' +
        '</div>',
    created: function() {
        publicationApi.get().then(response =>
            response.json().then(data =>
                data.forEach(publication => this.publications.push(publication))
            )
        )
    }
});

let app = new Vue({
    el: '#app',
    template: '<publications-list :publications="publications" />',
    data: {
        publications: []
    }
});