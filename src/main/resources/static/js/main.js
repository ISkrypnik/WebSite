
function getIndex(list, id) {
    for (let i = 0; i < list.length; i++) {
        if (list[i].id === id) {
            return i;
        }
    }

    return -1;
}

let publicationApi = Vue.resource('/publication{/id}');

Vue.component('publication-form', {
    props: ['publications', 'publicationAttr'],
    data: function() {
        return {
            id: '',
            name: '',
            text: ''
        }
    },
    watch: {
        publicationAttr: function(newVal, oldVal) {
            this.id = newVal.id;
            this.name = newVal.name;
            this.text = newVal.text;
        }
    },
    template:
        '<div>' +
        '<input type="text" placeholder="Write publication name" v-model="name"/>' +
        '<input type="text" placeholder="Write publication text" v-model="text"/>' +
        '<input type="button" value="Save" @click="save"/>' +
        '</div>',
    methods: {
        save: function() {
            let publication = {name: this.name, text: this.text};

            if (this.id) {
                publicationApi.update({id: this.id}, publication).then(result =>
                    result.json().then(data => {
                        let index = getIndex(this.publications, data.id);
                        this.publications.splice(index, 1, data);
                        this.id = '';
                        this.name = '';
                        this.text = '';
                    })
                )
            } else {
                publicationApi.save({}, publication).then(result =>
                    result.json().then(data => {
                        this.publications.push(data);
                        this.name = '';
                        this.text = ''
                    })
                )
            }
        }
    }
});

Vue.component('publication-row', {
    props: ['publication', 'publications','editMethod'],
    template:
        '<div>' +
        '<i>({{ publication.id }})</i> Name: {{ publication.name }}. Text: {{ publication.text }}' +
        '<span style="position: absolute; right: 0">' +
        '<input type="button" value="Edit" @click="edit" />' +
        '<input type="button" value="X" @click="del" />' +
        '</span>' +
        '</div>',
    methods: {
        edit: function () {
            this.editMethod(this.publication)
        },
        del: function () {
            publicationApi.remove({id: this.publication.id}).then(result => {
                if (result.ok) {
                    this.publications.splice(this.publications.indexOf(this.publication), 1)
                }
            })
        }
    }
});

Vue.component('publications-list', {
    props: ['publications'],
    data: function() {
        return {
            publication: null
        }
    },
    template:
        '<div style="position: relative; width: 300px;">' +
        '<publication-form :publications="publications" :publicationAttr="publication"/>' +
        '<publication-row v-for="publication in publications" :key="publication.id" :publication="publication" :editMethod="editMethod" ' +
        ':publications="publications"/>' +
        '</div>',
    created: function() {
        publicationApi.get().then(response =>
            response.json().then(data =>
                data.forEach(publication => this.publications.push(publication))
            )
        )
    },
    methods: {
        editMethod: function(publication) {
            this.publication = publication;
        }
    }
});

let app = new Vue({
    el: '#app',
    template: '<publications-list :publications="publications" />',
    data: {
        publications: []
    }
});