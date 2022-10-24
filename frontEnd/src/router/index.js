import Vue from "vue";
import VueRouter from "vue-router";
import store from '../store'

Vue.use(VueRouter);

const routes = [
    {
        path: "/login",
        name: "Login",
        component: () => import("../view/PhucLoiLogin.vue"),
    },
    {
        path: "/",
        name: "PhucLoiList",
        component: () => import("@/view/PhucLoiList.vue"),
    },
    {
        path: "/hr",
        name: "HrList",
        component: () => import("@/view/HrList.vue"),
    },
    {
      path: "/register_welfare",
      name: "RegisterWelfare",
      component: () => import("@/view/RegisterWelfare.vue"),
    },
    {
      path: "/check",
      name: "MyCheckBox",
      component: () => import("@/view/MyCheckBox.vue"),
    },
    {
      path: "/xetduyet",
      name: "xetduyet",
      component: () => import("../view/RegisterWelfare.vue"),
  },
    {
        path: '/logout',
        name: 'logout',
        beforeEnter (to, from, next) {
          store.dispatch('logout').then(() => {
            if (this.$store.state.auth.status.loggedIn) {
              return next('/')
            }
            location.reload()
          }).catch(reason => {
            console.log(reason)
          })
        }
    }
];

const router = new VueRouter({
    mode: "history",
    base: process.env.BASE_URL,
    routes,
});

export default router;
