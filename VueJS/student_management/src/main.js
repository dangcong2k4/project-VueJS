import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import VueAxios from 'vue-axios'
import axios from 'axios'

const app = createApp(App).use(router)
app.use(VueAxios, {$request:axios})
app.mount('#app')
