<template>
  <div>
    <div style="height:100vh;margin-left: 8vw">

      <h4 style="font-weight: bold;margin-top:5vh">计算机好课推荐</h4>
      <el-row :gutter="10">
        <el-col class="card"  :span="5" style="padding: 0;border: 0;margin-left:25px;margin-top:20px;width:85%"  v-for="courses in tableData">
          <el-card  @click="router.push({name:'blog-CourseInfoMain', params:{ id: courses.courseId,name: courses.courseName,teacherName:courses.teacher.name}})">
            <template #header>
              <div class="card-header">
                <img
                    :src=courses.courseCover
                    style="width:280px;height:130px;"/>
              </div>
            </template>
            <div class="text item">
              <h5 style="font-weight: bold">{{ courses.courseName }}</h5>
            </div>
            <div class="text item mt-1">
              <span style="font-size: 13px" v-if="courses.teacher!=null">{{ courses.teacher.graduationSchool }}</span>
              <span style="font-size: 13px" v-if="courses.teacher==null">行太学堂</span>
            </div>
            <div class="text item">
              <span style="font-size: 13px;color: gray" v-if="courses.teacher!=null">{{ courses.teacher.name }}</span>
              <span style="font-size: 13px" v-if="courses.teacher==null" >行太官方教师</span>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script setup>
import {ref, onMounted} from "vue";
import TypeIt from 'typeit';
import AOS from 'aos';
import 'aos/dist/aos.css';
import {useStore} from "@/stores";
import {
  Check,
  Delete,
  Edit,
  Message,
  Search,
  Star,
} from '@element-plus/icons-vue'
import router from "@/router";
import {get} from "@/net";

const text = ref(null)
const imgURL = ref('@/assets/img/index-bg/img_1.png')
const store = useStore()
const tableData = ref([])
store.auth.index = 2
get('/api/course/Courses', (message) => {
    // router.push('index')
  tableData.value = message
  console.log(message)
  }, () => {
    store.auth.user = null
  })


</script>

<style scoped>
* {
  padding: 0;
  margin: 0;
}
.card:hover{
  box-shadow: 4px 4px 5px 5px #f2f2f2;
  cursor: pointer
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

</style>