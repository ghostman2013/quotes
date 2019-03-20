const ROUTER = new VueRouter({
    routes: [
        { path: '/', name: 'top10   ', component: httpVueLoader('vue/top10.vue') },
        { path: '/users/new', name: 'new_user', component: httpVueLoader('vue/registration.vue') },
        { path: '/users/me', name: 'me', component: httpVueLoader('vue/profile.vue') },
        { path: '/quotes/flop10', name: 'flop10', component: httpVueLoader('vue/flop10.vue') },
        { path: '/quotes/last', name: 'last', component: httpVueLoader('vue/last.vue') },
        { path: '/quotes/new', name: 'new_quote', component: httpVueLoader('vue/new_quote.vue') }
    ]
});

ROUTER.beforeResolve((to, from, next) => {
    // If this isn't an initial page load.
    if (to.name) {
        // Start the route progress bar.
        NProgress.start()
    }
    next()
});

ROUTER.afterEach((to, from) => {
    // Complete the animation of the route progress bar.
    NProgress.done()
});

const LOADER = {
    template: `
      <div class="{'is-loading': loading}">
          <slot/>
      </div>
    `,
    props: ['loading']
};