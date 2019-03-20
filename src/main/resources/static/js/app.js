
Vue.use(VueAxios, axios);



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
                STORE.commit('SET_SIDEBAR', 'login');
            }).catch(function(response) {
                console.log(response);
                STORE.commit('SET_SIDEBAR', 'error');
            })
    },
    el: '#root',
    data: {
        navigation: [
            { title: 'Profile' },
            { title: 'Top 10' },
            { title: 'Flop 10' },
            { title: 'Last'}
        ],
        isNavVis: false
    },
    computed: {
        sidebar() { return STORE.getters.SIDEBAR; },
        content() { return STORE.getters.CONTENT; }
    },
    // methods: {
    //     submitLoginForm: function(e) {
    //         const form = this.$refs['login-form'];
    //         const formData = new FormData(form);
    //         axios.post(e.target.action, formData, {
    //             headers: {'Content-Type': 'multipart/form-data' }
    //         }).then(response => {
    //             this.$refs['sidebar'].innerHTML = response;
    //         }).catch(function () {
    //             alert("Authentication failed.");
    //         })
    //     }
    // }
});