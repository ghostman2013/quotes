Vue.use(Vuex);

const STORE = new Vuex.Store({
    state: {
        token: null
    },
    getters: {
        TOKEN: state => {
            return state.token;
        }
    },
    mutations: {
        INIT_TOKEN: (state) => {
            const data = window.localStorage.getItem('JwtToken');

            if (data) {
                const token = JSON.parse(data);
                state.token = token;
            }
        },
        SET_TOKEN: (state, payload) => {
            window.localStorage.setItem('JwtToken', JSON.stringify(payload));
            state.token = payload;
        },
        DELETE_TOKEN: (state) => {
            window.localStorage.removeItem("JwtToken");
            state.token = null;
        }
    },
    actions: {}
});

STORE.commit('INIT_TOKEN');