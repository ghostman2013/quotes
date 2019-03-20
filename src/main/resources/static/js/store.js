Vue.use(Vuex);

const STORE = new Vuex.Store({
    state: {
        sidebar: 'loading'
    },
    getters: {
        SIDEBAR: state => { return state.sidebar; }
    },
    mutations: {
        SET_SIDEBAR: (state, payload) => { state.sidebar = payload; }
    },
    actions: {}
});