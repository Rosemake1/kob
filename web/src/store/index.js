import {createStore} from 'vuex'
import moduleUser from './user'
import modulePk from './pk'
export default createStore({
    state:{
    },
    getters:{
    },
    mutations:{
    },
    actions:{
    },
    modules:{
        user:moduleUser,
        pk:modulePk
    }
})