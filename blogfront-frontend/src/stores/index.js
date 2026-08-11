import {ref, computed, reactive} from 'vue'
import {defineStore} from 'pinia'

export const useStore = defineStore('store', () => {
    const auth = reactive({
        user: null,
        index:0,
        course:null,
        userInfo:null,
        chapter:null,
    })

    return {auth}
})
