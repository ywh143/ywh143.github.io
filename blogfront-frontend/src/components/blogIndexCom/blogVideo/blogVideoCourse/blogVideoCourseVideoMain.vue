<template>
  <div>
    <el-card class="box-card" style="border: 0; box-shadow: none">
      <template #header>
        <div class="card-header">
          <span>课件</span>
          <el-button class="button" style="margin-left: 88%; width: 80px">查看帮助</el-button>
        </div>
      </template>

      <div>
        <video
            class="video-main"
            ref="videoPlayer"
            autoplay
            v-if="videoSrc !== '' && store.auth.user!=null"
            :src="videoSrc"
            :controls="true"
            controlsList="nodownload"
            :disablePictureInPicture="true"
            :hidden="hideVideo"
            @timeupdate="handleTimeUpdate"
            @loadedmetadata="handleMetadata"
        >
          <source :src="videoSource" type="video/mp4">
        </video>
        <video
            class="video-main"
            ref="videoPlayer"
            autoplay
            v-if="videoSrc !== '' && store.auth.user==null"
            :src="videoSrc"
            :controls="true"
            controlsList="nodownload"
            :disablePictureInPicture="true"
            :hidden="hideVideo"
        >
          <source :src="videoSource" type="video/mp4">
        </video>
        <p>当前播放时间：{{ currentTime }}</p>
        <p>视频总时间长度：{{ totalDuration }}</p>
      </div>
    </el-card>
  </div>
</template>

<script setup>
import {ref, onMounted} from 'vue';
import {get, post} from "@/net";
import {useStore} from "@/stores";
import {useRoute} from "vue-router";
import router from "@/router";
import {ElMessage} from "element-plus";


const store = useStore()
const route = useRoute()
const currentTime = ref(0);
const hideVideo = ref(false);
const videoSrc = ref(''); // 修改此行
const videoSource = '';
const tableData = ref([])
const totalDuration = ref(0);
const time = ref(0)
const videoPlayer = ref(null);


async function fetchTableData() {
  try {
    const message = await new Promise((resolve, reject) => {
      get('/api/courseResource/CourseResource/' + route.params.id, (message) => {
        tableData.value = message;
        resolve(message);
      });
    });
    videoSrc.value = tableData.value.resourceUrl; // 修改此行
    return tableData.value;
    // 在这里可以使用videoUrl进行后续操作
  } catch (error) {
    // 处理错误
    console.error(error);
  }
}

const setStartTime = () => {
  post('/api/learning/findLearningProgress', {userId: store.auth.userInfo.userId, chapterId: route.params.id},
      (message) => {
        console.log(message.currentTime)
        if (videoPlayer.value) {
          videoPlayer.value.currentTime = message.currentTime;
        }
      }, (message) => {
        post('/api/learning/insertLearningProgress',
            {userId: store.auth.userInfo.userId, chapterId: route.params.id},
            (message) => {
            })
      });

}
fetchTableData();
function handleTimeUpdate(event) {
  currentTime.value = event.target.currentTime;
  post('/api/learning/findLearningProgress', {userId: store.auth.userInfo.userId, chapterId: route.params.id},
      (message) => {
        post('/api/learning/updateLearningProgress',
            {
              userId: store.auth.userInfo.userId,
              chapterId: route.params.id,
              progress: currentTime.value / totalDuration.value,
              currentTime: currentTime.value

            }, () => {
            })
      })

}


function handleMetadata(event) {
  totalDuration.value = event.target.duration;
}

onMounted(async () => {
  await fetchTableData();
  setStartTime();
  // 其他渲染时需要执行的代码
});

</script>


<style>
.video-main {
  width: 100%;
  height: 75vh;
}
</style>
