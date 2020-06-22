<template>
    <div>
        <input type="text" placeholder="Write publication name" v-model="name"/>
        <input type="text" placeholder="Write publication text" v-model="text"/>
        <input type="button" value="Save" @click="save"/>
    </div>
</template>

<script>
    function getIndex(list, id) {
        for (let i = 0; i < list.length; i++) {
            if (list[i].id === id) {
                return i
            }
        }

        return -1
    }

    export default {
        props: ['publications', 'publicationAttr'],
        data() {
            return {
                id: '',
                name: '',
                text: ''
            }
        },
        watch: {
            publicationAttr: function(newVal, oldVal) {
                this.id = newVal.id
                this.name = newVal.name
                this.text = newVal.text
            }
        },
        methods: {
            save() {
                let publication = {name: this.name, text: this.text};

                if (this.id) {
                    this.$resource('/publication{/id}').update({id: this.id}, publication).then(result =>
                        result.json().then(data => {
                            let index = getIndex(this.publications, data.id)
                            this.publications.splice(index, 1, data)
                            this.id = '';
                            this.name = '';
                            this.text = '';
                        })
                    )
                } else {
                    this.$resource('/publication{/id}').save({}, publication).then(result =>
                        result.json().then(data => {
                            this.publications.push(data)
                            this.name = ''
                            this.text = ''
                        })
                    )
                }
            }
        }
    }
</script>

<style>

</style>