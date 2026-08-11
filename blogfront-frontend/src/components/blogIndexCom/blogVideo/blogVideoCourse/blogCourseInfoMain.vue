<template>
  <div>

    <el-card class="box-card" style="border:0;box-shadow: none">
      <template #header>
        <div class="card-header">
          <span>课程介绍</span>
          <el-button v-if="buyData.purchaseStatus===1" class="button" style="margin-left:85%;width:80px">查看帮助</el-button>
          <el-button v-if="buyData.purchaseStatus===0" class="button" @click="pay(111)" style="margin-left:85%;width:80px">购买课程</el-button>
        </div>
      </template>
      <h5>课程：{{tableData.courseName}}</h5>
      <br>
      <h5>介绍：{{ tableData.courseDescription }}</h5>


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
import {get,post} from "@/net";
import {ElMessage} from "element-plus";

const text = ref(null)
const imgURL = ref('@/assets/img/index-bg/img_1.png')
const store = useStore()
const route = useRoute()
const tableData = ref([])
const buyData = ref([])
const activeNames = ref(['1'])
const coursesName = ref('')
console.log(store.auth.userInfo)

if (store.auth.userInfo!=null){
  post('/api/UserCourseRelationship/userCourseRelationship',{userId:store.auth.userInfo.userId,courseId:route.params.id}, (message) => {
    // router.push('index')

    console.log('asdasdsa')
  })
}

if (store.auth.userInfo != null) {
  post('/api/UserCourseRelationship/userCourseRelationship', {
    userId: store.auth.userInfo.userId,
    courseId: route.params.id
  }, (message) => {
    // router.push('index')
    buyData.value = message
  })
}

const  pay = (row) => {
  window.open("http://localhost:2223/pc_pay?subject=" + store.auth.userInfo.userId + "&traceNo=" + route.params.id + "&totalAmount=" + 0.00)
  ElMessage.success("请求支付宝成功")
}

get('/api/course/Courses/' + route.params.id, (message) => {
  // router.push('index')
  tableData.value = message
  console.log(message)
})

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