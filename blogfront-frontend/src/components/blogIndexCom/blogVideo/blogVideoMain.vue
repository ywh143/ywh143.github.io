<template>
  <div style="height:100vh">
    <div style="weight:100%;height:70px;border-bottom: 1px solid gainsboro;background-color: white">
      <el-row :gutter="20" style="padding:10px">
        <el-col :span="3">
          <div style="border-right: 1px solid gainsboro;width:200px;text-align: center;height:50px;line-height:50px;">
            <img src="@/assets/img/blog-video/course/computer/1/school.png" width="140" height="50"/>
          </div>
        </el-col>
        <el-col :span="2">
          <div style="width:130px;height:50px;line-height:0px;margin-left:15px">
            <img :src="img" width="100" height="55" style="border-radius: 8px;border: 1px solid gainsboro"/>
          </div>
        </el-col>
        <el-col :span="11">
          <div>
            <ul>
              <ol>
                <h6 style="font-weight: bold;">{{ route.params.name }}&nbsp;&nbsp;<el-tag class="ml-2" size="large"
                                                                                          style="width:70px;font-weight: normal">
                  国家精品
                </el-tag>
                </h6>

              </ol>
              <ol>
                <span style="font-size:15px;">{{ route.params.teacherName }}</span>
              </ol>
            </ul>
          </div>
        </el-col>
        <el-col :span="8">
          <div style="line-height: 55px;margin-left:10vw">
            <el-button type="warning" round plain style="font-size:15px;width:120px;height:40px"><i
                class="bi bi-clipboard2-check"></i>&nbsp;&nbsp;课程评价
            </el-button>
            <el-button type="success" round plain style="font-size:15px;width:150px;height:40px"><i
                class="bi bi-clock"></i>&nbsp;&nbsp;我的学习时长
            </el-button>
          </div>
        </el-col>
      </el-row>
    </div>
    <div style="height:100vh;margin-top:5vh;margin-left: 4vw">
      <el-row :gutter="24">
        <el-col :span="5" style="height:70vh;padding:10px 10px;border-radius: 10px;background-color: white"
                class="shadow">
          <el-menu
              default-active="1"
              class="el-menu-vertical-demo"
              active-text-color="rgba(3,103,229)"
              style="border-radius: 15px;border:0;"
          >
            <el-menu-item index="1" style="width:100%;border-radius: 15px;font-size:18px"
                          @click="router.push({name:'blog-CourseInfoMain', params:{ id: store.auth.course,name: route.params.name,teacherName:route.params.teacherName}})">

              <i class="bi bi-collection-play"></i>
              <span>&nbsp;&nbsp;简介</span>
            </el-menu-item>

            <el-menu-item index="2" style="width:100%;margin-top:15px;border-radius: 15px;font-size:18px"
                          @click="router.push({name:'blog-CertificateMain', params:{ id: store.auth.course,name: route.params.name,teacherName:route.params.teacherName}})">

            <i class="bi bi-file-earmark-text"></i>
              <span>&nbsp;&nbsp;证书</span>
            </el-menu-item>

            <el-menu-item index="3" style="width:100%;margin-top:15px;border-radius: 15px;font-size:18px"
                          @click="router.push({name:'blog-CourseMain', params:{ id: store.auth.course,name: route.params.name,teacherName:route.params.teacherName}})">
              <i class="bi bi-collection-play"></i>
              <span>&nbsp;&nbsp;课件</span>
            </el-menu-item>
          </el-menu>

        </el-col>
        <el-col class="shadow" :span="18"
                style="height:100vh;padding:10px 10px;border-radius: 10px;background-color: white;margin-left:20px">
          <el-menu
              default-active="1"
              class="el-menu-vertical-demo"
              active-text-color="rgba(3,103,229)"
              style="border-radius: 15px;border:0;"
          >
            <router-view v-slot="{ Component }">
              <transition name="el-fade-in-linear">
                <component :is="Component"/>
              </transition>
            </router-view>
          </el-menu>

        </el-col>

      </el-row>

    </div>
  </div>
</template>

<script setup>
import {
  Check,
  Delete,
  Edit,
  Message,
  Search,
  Star,
} from '@element-plus/icons-vue'
import {ref, onMounted} from "vue";
import TypeIt from 'typeit';
import AOS from 'aos';
import 'aos/dist/aos.css';
import {useStore} from "@/stores";
import router from "@/router";
import {useRoute} from "vue-router";
import {get} from "@/net";

const route = useRoute()
const text = ref(null)
const imgURL = ref('@/assets/img/index-bg/img_1.png')
const store = useStore()
const data = ref([])
const img = ref('/src/assets/img/blog-video/course/computer/' + route.params.id + '/courseCover.png')

store.auth.course = route.params.id;
console.log(route.params.teacherName)
</script>

<style scoped>

* {
  padding: 0;
  margin: 0;
}


h1 {
  font-weight: 300;
  letter-spacing: 2px;
  font-size: 48px;
}

p {
  font-family: 'Lato', sans-serif;
  letter-spacing: 1px;
  font-size: 30px;
  color: #333333;
}

.header {
  position: relative;
  text-align: center;
  background: linear-gradient(60deg, white 0%, white 100%);
  color: white;
}

.inner-header {
  height: 45vh;
  width: 100%;
  margin: 0;
  padding: 0;
}

.flex {
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
}

.waves {
  position: relative;
  width: 100%;
  height: 15vh;
  margin-bottom: -7px;
  min-height: 100px;
  max-height: 150px;
}

.content {
  position: relative;
  margin-top: -45vh;
  text-align: center;
  font-weight: bold;
  color: white;
}

.parallax > use {
  /* 使use元素执行move-forever动画 */
  animation: move-forever 25s cubic-bezier(.55, .5, .45, .5) infinite;
}

.parallax > use:nth-child(1) {
  /* 延迟2秒启动动画  */
  animation-delay: -6s;
  /* 设置动画持续时间为7秒 */
  animation-duration: 4s;
}

.parallax > use:nth-child(2) {
  animation-delay: -6s;
  animation-duration: 4s;
}

.parallax > use:nth-child(3) {
  animation-delay: -6s;
  animation-duration: 4s;
}

.parallax > use:nth-child(4) {
  animation-delay: -3s;
  animation-duration: 4s;
}

@keyframes move-forever {
  0% {
    transform: translate3d(-90px, 0, 0);
  }

  100% {
    transform: translate3d(85px, 0, 0);
  }
}

@media (max-width: 768px) {
  .waves {
    height: 40px;
    min-height: 40px;
  }

  .content {
    height: 30vh;
  }

  h1 {
    font-size: 24px;
  }
}

.el-menu-item.is-active {
  background-color: rgba(3, 103, 229, 0.2) !important;
}


</style>