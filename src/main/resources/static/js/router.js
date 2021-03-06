const ROUTER = new VueRouter({
    linkActiveClass: 'is-active',
    routes: [
        { path: '/users/new', name: 'new_user', component: httpVueLoader('/vue/registration.vue') },
        { path: '/quotes/mine', name: 'mine', component: httpVueLoader('/vue/mine.vue') },
        { path: '/quotes/top10', name: 'top10   ', component: httpVueLoader('/vue/top10.vue') },
        { path: '/quotes/flop10', name: 'flop10', component: httpVueLoader('/vue/flop10.vue') },
        { path: '/quotes/last', name: 'last', component: httpVueLoader('/vue/last.vue') },
        { path: '/quotes/new', name: 'new_quote', component: httpVueLoader('/vue/new_quote.vue') },
        { path: '*', redirect: '/quotes/top10' }
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