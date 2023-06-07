import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    name: 'list',
    component: () => import('../components/ListStudent.vue')
  },
   {
    path: '/create',
    name: 'create',
    component: () => import('../components/CreateStudent.vue')
  },
  {
    path: '/update/:id',
    name: 'update',
    component: () => import('../components/UpdateStudent.vue')
  }

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
