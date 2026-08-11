import {createRouter, createWebHistory} from 'vue-router'
import {useStore} from '@/stores';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
   {
      path:'/',
      name:"blogIndex",
      component: () => import('@/views/blogIndex.vue'),
      children: [
          {
              path: '/',
              name: 'blog-Index',
              component: () => import('@/components/blogIndexCom/blogMain.vue'),
              meta: {
                  title : '博客首页'
              }
          },
          {
              path: '/resources',
              name: 'blog-Resources',
              component: () => import('@/components/blogIndexCom/blogResources.vue'),
              meta: {
                  title : '资源首页'
              }
          },
          {
              path: '/video',
              name: 'blog-Video',
              component: () => import('@/components/blogIndexCom/blogVideo/blogVideo.vue'),
              meta: {
                  title : '视频首页'
              }
          },
          {
              path: '/videoMain/:id/:name/:teacherName',
              name: 'blog-VideoMain',
              component: () => import('@/components/blogIndexCom/blogVideo/blogVideoMain.vue'),
              meta: {
                  title : '视频详细页'
              },
              children:[
                  {
                      path: '/videoMain/:id/:name/:teacherName',
                      name: 'blog-CourseInfoMain',
                      component: () => import('@/components/blogIndexCom/blogVideo/blogVideoCourse/blogCourseInfoMain.vue'),
                      meta: {
                          title : '介绍页面'
                      }
                  },
                  {
                      path: '/certificateMain/:id/:name/:teacherName',
                      name: 'blog-CertificateMain',
                      component: () => import('@/components/blogIndexCom/blogVideo/blogVideoCourse/blogCertificateMain.vue'),
                      meta: {
                          title : '证书页面'
                      }
                  },
                  {
                  path: '/CourseMain/:id/:name/:teacherName',
                  name: 'blog-CourseMain',
                  component: () => import('@/components/blogIndexCom/blogVideo/blogVideoCourse/blogVideoCourseMain.vue'),
                  meta: {
                      title : '课件页面'
                  }
              },
                  {
                      path: '/CourseVideoMain/:id/:name/:teacherName',
                      name: 'blog-CourseVideoMain',
                      component: () => import('@/components/blogIndexCom/blogVideo/blogVideoCourse/blogVideoCourseVideoMain.vue'),
                      meta: {
                          title: '课件视频页面'
                      }
                  }
              ]
          },{
              path: '/personInfo',
              name: 'blog-personInfo',
              component: () => import('@/components/blogIndexCom/blogPersonInfo.vue'),
              meta: {
                  title : '博客个人信息'
              }
          },{
              path: '/article',
              name: 'blog-Article',
              component: () => import('@/components/blogIndexCom/blogArticle/blogArticle.vue'),
              meta: {
                  title : '博客内容'
              },
              props:true
          },{
              path: '/articleDetail',
              name: 'blog-Article-Detail',
              component: () => import('@/components/blogIndexCom/blogArticle/blogArticleDetail.vue'),
              meta: {
                  title : '博客详情'
              },
              props:true
          },{
              path: '/articleAdd',
              name: 'blog-Article-Add',
              component: () => import('@/components/blogIndexCom/blogArticle/blogArticleAdd.vue'),
              meta: {
                  title : '博客发布'
              },
              props:true
          },{
              path: '/articleManagement',
              name: 'blog-Article-Management',
              component: () => import('@/components/blogIndexCom/blogArticle/blogArticleManagement.vue'),
              meta: {
                  title : '博客管理'
              },
              props:true
          },{
              path: '/articleUpdate',
              name: 'blog-Article-Update',
              component: () => import('@/components/blogIndexCom/blogArticle/blogArticleUpdate.vue'),
              meta: {
                  title : '博客管理'
              },
              props:true
          }, {
              path: '/helpDocument',
              name: 'blog-helpDocument',
              component: () => import('@/components/blogIndexCom/blogHelpDocument.vue'),
              meta: {
                  title : '博格帮助文档'
              }
          },
      ]
    },
      {
          path: '/login',
          name: "login",
          component: () => import('@/views/Login.vue'),
          children: [
              {
                  path: '/login',
                  name: 'blog-login',
                  component: () => import('@/components/LoginCom/LoginPage.vue')
              },
              {
                  path: 'register',
                  name: 'blog-register',
                  component: () => import('@/components/LoginCom/RegisterPage.vue')
              },
              {
                  path: 'forget',
                  name: 'blog-forget',
                  component: () => import('@/components/LoginCom/ForgetPage.vue')
              }
          ]
      },
      {
          path: '/test',
          name: 'blog-test',
          component: () => import('@/components/LoginCom/resourcesPage.vue')
      }

  ]
})

//路由守卫
router.beforeEach((to, from, next) => {
  const store = useStore()
  next()

})

export default router
