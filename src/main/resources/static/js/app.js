Vue.component('loading', {
    template: '<div class="has-text-centered" style="margin: 16px;">Loading...</div>'
});
Vue.component('error', {
    template: '<div class="has-text-centered" style="margin: 16px;">Error!</div>'
});

const app = new Vue({
    router: ROUTER,
    el: '#root',
    data: {
        navigation: [
            { title: 'Profile', link: "#/users/me" },
            { title: 'Top 10', link: "#/" },
            { title: 'Flop 10', link: "#/quotes/flop10" },
            { title: 'Last', link: "#/quotes/last" }
        ],
        isNavVis: false
    },
    computed: {
        token() {
            return STORE.getters.TOKEN;
        },
    },
    components: {
        'login': httpVueLoader('vue/login.vue'),
        'greeting': httpVueLoader('vue/greeting.vue')
    }
});