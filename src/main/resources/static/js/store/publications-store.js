import Vue from 'vue'
import Vuex from 'vuex'
import publicationsApi from 'api/publications'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        publications: []
    },
    getters: {
        publications: state => state.publications
    },
    mutations: {
        addPublicationMutation(state, publication) {
            state.publications = [
                ...state.publications,
                publication
            ]
        },
        updatePublicationMutation(state, publication) {
            const updateIndex = state.publications.findIndex(item => item.id === publication.id)
            state.publications = [
                ...state.publications.slice(0, updateIndex),
                publication,
                ...state.publications.slice(updateIndex + 1)
            ]
        },
        removePublicationMutation(state, publication) {
            const removeIndex = state.publications.findIndex(item => item.id === publication.id)
            if (removeIndex > -1) {
                state.publications = [
                    ...state.publications.slice(0, removeIndex),
                    ...state.publications.slice(removeIndex + 1)
                ]
            }
        }
    },
    actions: {
        async addPublicationAction({ commit, state }, publication) {
            const result = await publicationsApi.save({}, publication)
            const data = await result.json()
            const index = state.publications.findIndex(item => item.id === data.id)

            if (index > -1) {
                commit('updatePublicationMutation', data)
            } else {
                commit('addPublicationMutation', data)
            }
        },
        async updatePublicationAction({commit}, publication) {
            const result = await publicationsApi.update({id: this.id}, publication)
            const data = await result.json()
            commit('updatePublicationMutation', data)
        },
        async removePublicationAction({commit}, publication) {
            const result = await publicationsApi.remove({id: publication.id})
            if (result.ok) {
                commit('removePublicationMutation', publication)
            }
        },
        async initPublicationsAction({commit}) {
            const result = await publicationsApi.getAll()
            const data = await result.json()
            const publicationsList = JSON.parse(JSON.stringify(data))
            publicationsList.forEach(publication => commit('addPublicationMutation', publication))
        }
    }
})