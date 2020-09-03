<template>
    <div>
        <input type="text" placeholder="Write publication name" v-model="name"/>
        <input type="text" placeholder="Write publication text" v-model="text"/>
        <input type="button" value="Save" @click="save"/>
    </div>
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
            publicationAttr: function(newVal, oldVal) {
                this.id = newVal.id
                this.name = newVal.name
                this.text = newVal.text
            }
        },
        methods: {
          ...mapActions(['addPublicationAction', 'updatePublicationAction']),
            save() {
                let publication = {name: this.name, text: this.text};

                if (this.id) {
                  //Moved to publication-store.updatePublicationAction
                  //   this.$resource('/publication{/id}').update({id: this.id}, publication).then(result =>
                  //       result.json().then(data => {
                  //           let index = getIndex(this.publications, data.id)
                  //           this.publications.splice(index, 1, data)
                  //           this.id = '';
                  //           this.name = '';
                  //           this.text = '';
                  //       })
                  //   )
                  this.updatePublicationAction(publication)
                  this.id = ''
                  this.name = ''
                  this.text = ''
                } else {
                  this.addPublicationAction(publication)
                  this.name = ''
                  this.text = ''
                  //Moved to publication-store.addPublicationAction
                    // this.$resource('/publication{/id}').save({}, publication).then(result =>
                    //     result.json().then(data => {
                    //         this.publications.push(data)
                    //         this.name = ''
                    //         this.text = ''
                    //     })
                    // )
                }
            }
        }
    }
</script>

<style>

</style>