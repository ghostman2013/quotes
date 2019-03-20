Vue.use(Vuex);

const STORE = new Vuex.Store({
    state: {
        sidebar: 'loading',
        content: 'loading'
    },
    getters: {
        SIDEBAR: state => { return state.sidebar; },
        CONTENT: state => { return state.content; }
    },
    mutations: {
        SET_SIDEBAR: (state, payload) => { state.sidebar = payload; },
        SET_CONTENT: (state, payload) => { state.content = payload; }
    },
    actions: {}
});