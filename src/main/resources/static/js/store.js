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

axios.interceptors.request.use(
    function(config) {
        const token = STORE.getters.TOKEN;

        if (token) {
            console.log("Authorized request: " + token.token);
            config.headers.Authorization = `Bearer ${token.token}`;
        }

        return config;
    },
    function(err) { return Promise.reject(err); }
);

axios.interceptors.response.use(response => {
    return response;
}, error => {
    if (error.response.status === 401) {
        console.log("Unauthorized request.");
        STORE.commit("DELETE_TOKEN");
    }
    return error;
});