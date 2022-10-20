import Vue from "vue";
import App from "./App.vue";
import components from "./components";
import "@/assets/css/main.css";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import router from './router'
import store from './store'
Vue.config.productionTip = false;

Vue.use(components);

Vue.use(ElementUI);

new Vue({
  store,
  router,
  render: (h) => h(App)
}).$mount("#app");
