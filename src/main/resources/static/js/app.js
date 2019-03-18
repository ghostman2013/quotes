document.addEventListener('DOMContentLoaded', function() {
    const burger = document.querySelector(".navbar-burger");
    burger.addEventListener('click', function() {
        burger.classList.toggle("is-active");
        const menu = document.querySelector(".navbar-menu");
        menu.classList.toggle("is-active");
    });
});

Vue.use(Vuex);

const store = new Vuex.Store({
    state: {
        navigation: [
            { title: 'Profile' },
            { title: 'Top 10' },
            { title: 'Flop 10' },
            { title: 'Last'}
        ],
        sidebar: 'loading',
        content: 'loading',
        isUser: isUser
    },
    getters: {
        NAVIGATION: state => { return state.navigation; },
        SIDEBAR: state => { return state.sidebar; },
        CONTENT: state => { return state.content; },
        IS_USER: state => { return state.isUser; }
    },
    mutations: {
        SET_SIDEBAR: (state, payload) => { state.sidebar = payload; },
        SET_CONTENT: (state, payload) => { state.content = payload; },
        SET_IS_USER: (state, payload) => { state.isUser = payload; }
    },
    actions: {}
});

Vue.component('loading', {
    template: '<div class="has-text-centered" style="margin: 16px;">Loading...</div>'
});
Vue.component('error', {
    template: '<div class="has-text-centered" style="margin: 16px;">Error!</div>'
});

const app = new Vue({
    beforeMount() {
        axios.get('vue/login.vue')
            .then(function (response) {
                const login = Vue.compile(response.data);
                Vue.component('login', login);

                if (!store.getters.IS_USER) {
                    store.commit('SET_SIDEBAR', 'login');
                }
            }).catch(function(response) {
                console.log(response);

                if (!store.getters.IS_USER) {
                    store.commit('SET_SIDEBAR', 'error');
                }
            })
    },
    el: '#root',
    store,
    computed: {
        sidebar() { return this.$store.getters.SIDEBAR; },
        content() { return this.$store.getters.CONTENT; },
        navigation() { return this.$store.getters.NAVIGATION; }
    },
    methods: {
        submitLoginForm: function(e) {
            const form = this.$refs['login-form'];
            const formData = new FormData(form);
            axios.post(e.target.action, formData, {
                headers: {'Content-Type': 'multipart/form-data' }
            }).then(response => {
                this.$refs['sidebar'].innerHTML = response;
            }).catch(function () {
                alert("Authentication failed.");
            })
        }
    }
});