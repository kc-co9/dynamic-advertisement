import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
    {
        path: '/login',
        component: () => import('@/views/login/index'),
        hidden: true
    },

    {
        path: '/404',
        component: () => import('@/views/404'),
        hidden: true
    },

    {
        path: '/',
        component: Layout,
        redirect: '/dashboard',
        children: [{
            path: 'dashboard',
            name: 'Dashboard',
            component: () => import('@/views/dashboard/index'),
            meta: {title: '主页', icon: 'dashboard'}
        }]
    },

    {
        path: '/module',
        component: Layout,
        name: 'Example',
        meta: {title: '模块管理', icon: 'el-icon-s-help'},
        children: [
            {
                path: 'index',
                name: 'ModuleList',
                component: () => import('@/views/module/moduleList'),
                meta: {title: '模块管理', icon: 'el-icon-s-help'}
            }, {
                path: 'detail',
                name: 'ModuleDetail',
                component: () => import('@/views/module/moduleDetail'),
                meta: {title: '模块详情', icon: 'el-icon-s-help'},
                hidden: true
            }]
    },

    {
        path: '/config',
        component: Layout,
        name: 'Example',
        meta: {title: '配置管理', icon: 'el-icon-s-help'},
        children: [
            {
                path: 'attributeList',
                name: 'ConfigAttributeList',
                component: () => import('@/views/config/configAttributeList'),
                meta: {title: '属性列表', icon: 'el-icon-s-help'}
            }, {
                path: 'attributeDetail',
                name: 'ConfigAttributeDetail',
                component: () => import('@/views/config/configAttributeDetail'),
                meta: {title: '属性详情', icon: 'el-icon-s-help'},
                hidden: true
            }, {
                path: 'configList',
                name: 'ConfigList',
                component: () => import('@/views/config/configList'),
                meta: {title: '配置列表', icon: 'el-icon-s-help'}
            }, {
                path: 'configDetail',
                name: 'ConfigDetail',
                component: () => import('@/views/config/configDetail'),
                meta: {title: '配置详情', icon: 'el-icon-s-help'},
                hidden: true
            }]
    },

    // 404 page must be placed at the end !!!
    {path: '*', redirect: '/404', hidden: true}
]

const createRouter = () => new Router({
    // mode: 'history', // require service support
    scrollBehavior: () => ({y: 0}),
    routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
    const newRouter = createRouter()
    router.matcher = newRouter.matcher // reset router
}

export default router
