<template>
  <div>

    <el-card class="box-card" style="border:0;box-shadow: none">
      <template #header>
        <div class="card-header">
          <span>课程介绍</span>
          <el-button class="button" style="margin-left:85%;width:80px">查看帮助</el-button>
        </div>
      </template>
      <h5 v-if="tableData.learningProgress<90">你还没观看完所有视频</h5>
      <div v-if="tableData.learningProgress >= 90">

        <h3 style="font-weight: bold;position: absolute;margin-top:16vh;z-index:99;margin-left:16vw;font-family: 方正行楷简体">行太不面对</h3>
        <h1 class="name" style="font-weight: bold;position: absolute;margin-top:30vh;z-index:99;margin-left:16vw;font-family: 方正舒体简体">{{ store.auth.userInfo.name }}</h1>
        <h3 style="font-weight: bold;position: absolute;margin-top:44vh;z-index:99;margin-left:22vw;font-family: 楷体">{{ route.params.name }}</h3>
        <h1 style="font-weight: bold;position: absolute;margin-top:55vh;z-index:99;margin-left:35vw;font-family: 草檀斋毛泽东字体">{{ route.params.teacherName }}</h1>

        <el-image style="margin-left:10%;width:930px" src="https://xmut-blog.oss-cn-shenzhen.aliyuncs.com/certificate1.png">

        </el-image>
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
import {ref, onMounted, watch} from "vue";
import 'aos/dist/aos.css';
import {useStore} from "@/stores";
import {useRoute} from "vue-router";
import router from "@/router";
import {get, post} from "@/net";

const text = ref(null)
const imgURL = ref('@/assets/img/index-bg/img_1.png')
const store = useStore()
const route = useRoute()
const tableData = ref([])
const activeNames = ref(['1'])
const coursesName = ref('')

if (store.auth.userInfo != null) {
  post('/api/UserCourseRelationship/userCourseRelationship', {
    userId: store.auth.userInfo.userId,
    courseId: route.params.id
  }, (message) => {
    // router.push('index')
    tableData.value = message
  })
}


</script>

<style scoped>

* {
  padding: 0;
  margin: 0;
}


@font-face {
  font-family: 'maozedong';
  src: url('@/assets/font/maozedong.ttf') format('ttf');
}

@font-face {
  font-family: '字魂138号';
  src: url('@/assets/font/字魂138号-霸燃手书.ttf') format('ttf');
}

.name{
  font-family: '字魂138号';
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