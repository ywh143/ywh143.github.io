<template>
  <div>

    <el-card class="box-card" style="border:0;box-shadow: none">
      <template #header>
        <div class="card-header">
          <span>课件</span>
          <el-button class="button" style="margin-left:88%;width:80px">查看帮助</el-button>
        </div>
      </template>
      <h1 v-if="tableData.length===0" style="font-weight: bold;margin-left:25vw">课程暂未开放</h1>
      <div class="demo-collapse" style="cursor: pointer;">

        <el-collapse v-model="activeNames">

          <el-collapse-item :title="route.params.name"  :name="courses.chapterId" v-for="courses in tableData">

            <div @click="router.push({name:'blog-CourseVideoMain', params:{ id: courses.chapterId,name: route.params.name,teacherName:route.params.teacherName}})">
              <div style="font-size:15px;">
                <el-button :icon="Check" circle size="small"/>
                {{ courses.sortOrder }}.&nbsp; {{ courses.chapterTitle }}{{ coursesName.value }}
              </div>
              <div class="mt-2">
                <el-tag style="margin-left:35px;">视频</el-tag>
                &nbsp{{ courses.chapterTitle }}
              </div>
            </div>
          </el-collapse-item>

        </el-collapse>
      </div>


    </el-card>


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
import 'aos/dist/aos.css';
import {useStore} from "@/stores";
import {useRoute} from "vue-router";
import router from "@/router";
import {get} from "@/net";

const text = ref(null)
const imgURL = ref('@/assets/img/index-bg/img_1.png')
const store = useStore()
const route = useRoute()
const tableData = ref([])
const activeNames = ref(['1'])
const coursesName = ref('')


get('/api/chapter/Chapter/' + route.params.id, (message) => {
  // router.push('index')
  tableData.value = message
  console.log(message)
})
console.log(route.params.name)

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

.demo-collapse :hover {
  color: rgba(3, 103, 229);
}
</style>