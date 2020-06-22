<template>
    <div>
        <i>({{ publication.id }})</i> Name: {{ publication.name }}. Text: {{ publication.text }}
        <span style="position: absolute; right: 0">
        <input type="button" value="Edit" @click="edit"/>
        <input type="button" value="X" @click="del"/>
        </span>
    </div>
</template>

<script>
    export default {
        props: ['publication', 'editPublication', 'deletePublication'],
        methods: {
            edit() {
                this.editPublication(this.publication)
            },
            del() {
                this.deletePublication(this.publication)
            }
        }
    }
</script>

<style>

</style>





